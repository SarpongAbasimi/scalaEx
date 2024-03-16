import kstreams.serdes.{CustomSerdes, GenericSerde}
import kstreams.topology.UserPostTopology
import model.UserPostWithCount
import org.apache.kafka.streams.state.{HostInfo, QueryableStoreTypes, ReadOnlyKeyValueStore}
import org.apache.kafka.streams.{KafkaStreams, StoreQueryParameters, Topology}
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

//    val stateStore: ReadOnlyKeyValueStore[String, UserPostWithCount] = kafkaStreams.store(
//      StoreQueryParameters.fromNameAndType(
//        "learning",
//        QueryableStoreTypes.keyValueStore[String,  UserPostWithCount]()
//      ))
//



    kafkaStreams.start()
    val userPostService = new  UserPostService(hostInfo, kafkaStreams)
    userPostService.start()
  }
}
