import kstreams.topology.BookTopology
import kstreams.serdes.CustomSerdes
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig, Topology}
import java.util.Properties



object BookTopologyMain {
  def main(args: Array[String]): Unit = {

    val customSerde = new CustomSerdes()
    val topology: Topology =  BookTopology.build(customSerde)


    val properties: Properties = new Properties()
    properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "learn_app")



    val streams: KafkaStreams = new KafkaStreams(topology, properties)

    streams.start()
  }
}
