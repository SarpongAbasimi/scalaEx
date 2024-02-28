package kstreams.topology


import kstreams.serdes.{CustomSerdes, GenericSerde}
import model.Books
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{Consumed, KStream, Produced}
import org.apache.kafka.streams.scala.serialization.Serdes


object BookTopology {

  def build(customSerde: GenericSerde): Topology =  {
    val builder: StreamsBuilder = new StreamsBuilder()
     val stream = builder.stream[Array[Byte], Books]("users")(Consumed.`with`(Serdes.byteArraySerde, new CustomSerdes()
       .create[Books]))


    val filteredStream = stream.filterNot((_, books) => books.goodRating == false)
      .peek((_,b) => println(s"Data running through the stream is $b"))

    val branches: Array[KStream[Array[Byte], Books]] = filteredStream
      .branch(
      (_, value) => value.lang == "twi",
      (_, value) => value.lang != "twi")


    val twiBranch: KStream[Array[Byte], Books] = branches(0)
    val otherBranch: KStream[Array[Byte], Books] = branches(1)


    val mergedStream: KStream[Array[Byte], Books] = twiBranch.merge(otherBranch).mapValues(input => {
      input.copy(randomData = s"${input.title} has a rating of ${input.randomData}")
    })


    mergedStream.to("sink")(Produced.`with`(Serdes.byteArraySerde, customSerde.create[Books]))


    builder.build()
  }
}


