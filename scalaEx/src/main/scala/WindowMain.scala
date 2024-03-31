import kstreams.serdes.CustomSerdes
import kstreams.topology.WindowTopology
import org.apache.kafka.streams.{KafkaStreams, Topology}
import utils.Utils

object WindowMain {
  def main(args: Array[String]): Unit = {
    val customSerde = new CustomSerdes
    val windowTopology: Topology = new WindowTopology(customSerde).create
    val kafkaStreams = new KafkaStreams(
      windowTopology,
      Utils.createProperties("learn_window")
    )



    kafkaStreams.start()
  }
}
