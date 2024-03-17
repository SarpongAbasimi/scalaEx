import kstreams.serdes.{CustomSerdes, GenericSerde}
import kstreams.topology.UserPostTopology
import org.apache.kafka.streams.state.HostInfo
import org.apache.kafka.streams.{KafkaStreams, Topology}
import service.UserPostService
import utils.Utils

object UserPostMain {
  def main(args: Array[String]): Unit = {

    val customSerdes: GenericSerde = new CustomSerdes()
    val topology: Topology = new UserPostTopology(customSerdes).build
    val kafkaStreams: KafkaStreams = new KafkaStreams(topology, Utils.createProperties("learn_join"))
    val host: String = "localhost"
    val port: Int = 8080
    val hostInfo: HostInfo = new HostInfo(host, port)


    kafkaStreams.start()
    val userPostService = new  UserPostService(hostInfo, kafkaStreams)
    userPostService.start()
  }
}
