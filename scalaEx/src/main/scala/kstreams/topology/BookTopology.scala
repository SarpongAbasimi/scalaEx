package kstreams.topology


import kstreams.serdes.{CustomSerdes, GenericSerde}
import model.{Books, English, German, Ghanaian, Italian}
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{Branched, Consumed, KStream, Produced}
import org.apache.kafka.streams.scala.serialization.Serdes


object BookTopology {

  def build(customSerde: GenericSerde): Topology =  {
    val builder: StreamsBuilder = new StreamsBuilder()
     val stream = builder.stream[Array[Byte], Books]("users")(Consumed.`with`(Serdes.byteArraySerde, new CustomSerdes().create[Books]))


    val filteredStream = stream.filterNot((_, books) => books.language == "italian")
      .peek((_,b) => println(s"Data running through the stream is ${b.name}"))

    val branch: Map[String, KStream[Array[Byte], Books]] = filteredStream.split()
      .branch((_, value) => value.language == "twi", Branched.as("twi"))
      .defaultBranch(Branched.as("other"))

    val twiBranch: KStream[Array[Byte], Books] = branch.get("twi").get
    val otherBranch: Option[KStream[Array[Byte], Books]] = branch.get("other")

    val transformedBooks: KStream[Array[Byte], Books] = otherBranch.get.mapValues { value =>
      value match {
        case it  @ Italian(name, _, _) => it.copy(name = s"$name  mapping")
        case eng @ English(name, _, _) => eng.copy(name = s"$name is eng")
        case gh  @ Ghanaian(name, _, _) => gh.copy(name = s"$name no y3 f3")
        case ger @ German(name, _, _) => ger.copy(name = s"$name Das is good")
      }
    }

    val mergedStream: KStream[Array[Byte], Books] = twiBranch.merge(transformedBooks)


    mergedStream.to("sink")(Produced.`with`(Serdes.byteArraySerde, customSerde.create[Books]))


    builder.build()
  }
}
