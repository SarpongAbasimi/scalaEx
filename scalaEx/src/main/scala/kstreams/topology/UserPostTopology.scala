package kstreams.topology

import kstreams.serdes.GenericSerde
import model.{Post, UserPostWithCount, Users, UsersPost}
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.scala.kstream.Materialized
import org.apache.kafka.streams.scala.kstream.{Consumed, Grouped, Joined, KGroupedStream, KStream, KTable}
import org.apache.kafka.streams.scala.{ByteArrayKeyValueStore, StreamsBuilder}
import org.apache.kafka.streams.scala.serialization.Serdes

class UserPostTopology(customSerdes: GenericSerde) {

  def build: Topology = {

    val builder = new StreamsBuilder()

  val users: KTable[String, Users] = builder.table[String, Users]("users")(Consumed.`with`[String, Users](
    Serdes.stringSerde, customSerdes.create[Users]))

    val post: KStream[String, Post] = builder.stream[String, Post]("post")(Consumed.`with`[String, Post](
        Serdes.stringSerde, customSerdes.create[Post]))
      .selectKey((_, post) => post.userId.toString)

    val joinedParams: Joined[String, Post, Users] = Joined
      .`with`[String, Post, Users](
        Serdes.stringSerde,
        customSerdes.create[Post],
        customSerdes.create[Users]
      )

    val userPostJoiner: (Post, Users) => UsersPost = (p, u) => UsersPost(u.id, u.name, u.email, p.title, p.id)

    val joinedStream: KStream[String, UsersPost] = post.join[Users, UsersPost](users)(userPostJoiner)(joinedParams)

    val groupedStream: KGroupedStream[String, UsersPost] = joinedStream
      .groupByKey(Grouped.`with`[String, UsersPost]
        (Serdes.stringSerde, customSerdes.create[UsersPost]))

//    val count: KTable[String, Long] = groupedStream.count()(Materialized.`with`(Serdes.stringSerde, Serdes.longSerde))

    val aggregator : (String, UsersPost, UserPostWithCount) => UserPostWithCount = (_, usersPost, userPostWithCount) => {
      userPostWithCount.create(usersPost)
    }

    groupedStream
      .aggregate[UserPostWithCount](UserPostWithCount())(aggregator)(
        Materialized.as[String, UserPostWithCount, ByteArrayKeyValueStore]("learning")(
          Serdes.stringSerde, customSerdes.create[UserPostWithCount]
        )
      )


    builder.build()
  }
}
