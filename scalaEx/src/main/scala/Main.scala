import kstreams.kstream.KStreamBuilder
import model.{Books, English, Ghanaian, Italian}
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.Printed
import org.apache.kafka.streams.scala.kstream
import org.apache.kafka.streams.scala.kstream.{Branched, KStream}

import java.util.Properties



object Main {
  def main(args: Array[String]): Unit = {
    val kStream: kstream.KStream[Array[Byte], Books] =  KStreamBuilder.create[Books]

    val properties: Properties = new Properties()
    properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "learn_app")

   val filteredStream: KStream[Array[Byte], Books] =  kStream.filterNot((_, books) => books.language == "italian")

    filteredStream.print(Printed.toSysOut[Array[Byte], Books].withLabel("logs-from-stream"))

    val branch: Map[String, KStream[Array[Byte], Books]] = filteredStream.split()
      .branch((_, value) => value.language == "twi", Branched.as("twi"))
      .defaultBranch(Branched.as("other"))



    val twiBranch: Option[KStream[Array[Byte], Books]] = branch.get("twi")
    val otherBranch: Option[KStream[Array[Byte], Books]] = branch.get("other")


  }
}
