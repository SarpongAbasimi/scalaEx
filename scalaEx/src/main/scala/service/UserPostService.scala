package service

import io.javalin.Javalin
import io.javalin.http.Context
import model.UserPostWithCount
import org.apache.kafka.streams.{KafkaStreams, StoreQueryParameters}
import org.apache.kafka.streams.state.{HostInfo, QueryableStoreTypes, ReadOnlyKeyValueStore}


class UserPostService(host: HostInfo, kafkaStreams: KafkaStreams) {
  def getStateStore: ReadOnlyKeyValueStore[String, UserPostWithCount] = {
    kafkaStreams.store(
      StoreQueryParameters.fromNameAndType(
        "learning",
        QueryableStoreTypes.keyValueStore[String, UserPostWithCount]()
      ))
  }


  def start(): Unit = {
    val app = Javalin.create().start(host.port())
    app.get("/app/:key", getKey)
  }

  def getKey(context: Context): Context = {
    val productId = context.pathParam("key")

   val result: UserPostWithCount = getStateStore.get(productId)

    context.json(result.toString)
  }
}
