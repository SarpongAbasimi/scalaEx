import kstreams.serdes.{CustomSerdes, GenericSerde}
import kstreams.topology.UserPostTopology
import org.apache.kafka.streams.{KafkaStreams, Topology}
import utils.Utils

object UserPostMain {
  def main(args: Array[String]): Unit = {

    val customSerdes: GenericSerde = new CustomSerdes()
    val topology: Topology = new UserPostTopology(customSerdes).build
    val kafkaStreams = new KafkaStreams(topology, Utils.createProperties("learn_join"))

    kafkaStreams.start()
  }
}
