import kstreams.topology.BookTopology
import kstreams.serdes.CustomSerdes
import org.apache.kafka.streams.{KafkaStreams, Topology}
import utils.Utils


object BookTopologyMain {
  def main(args: Array[String]): Unit = {

    val customSerde = new CustomSerdes()
    val topology: Topology =  BookTopology.build(customSerde)

    val streams: KafkaStreams = new KafkaStreams(topology, Utils.createProperties("learn_app"))

    streams.start()
  }
}
