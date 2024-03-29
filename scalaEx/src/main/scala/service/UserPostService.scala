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
    val app: Javalin = Javalin.create().start(host.port())
    app.get("/app/{key}", context => {
      getKey(context)
    })
  }

  def getKey(context: Context): Unit = {
    val productId = context.pathParam("key")

   val result: UserPostWithCount = getStateStore.get(productId)

    context.json(result.toString)
  }
}
