import kstreams.serdes.{CustomSerdes, GenericSerde}
import kstreams.topology.UserPostTopology
import model.UserPostWithCount
import org.apache.kafka.streams.state.{ QueryableStoreTypes, ReadOnlyKeyValueStore}
import org.apache.kafka.streams.{KafkaStreams, StoreQueryParameters, Topology}
import utils.Utils

object UserPostMain {
  def main(args: Array[String]): Unit = {

    val customSerdes: GenericSerde = new CustomSerdes()
    val topology: Topology = new UserPostTopology(customSerdes).build
    val kafkaStreams = new KafkaStreams(topology, Utils.createProperties("learn_join"))


    val stateStore: ReadOnlyKeyValueStore[String, UserPostWithCount] = kafkaStreams.store(
      StoreQueryParameters.fromNameAndType(
        "learning",
        QueryableStoreTypes.keyValueStore[String,  UserPostWithCount]()
      ))




    kafkaStreams.start()
  }
}
