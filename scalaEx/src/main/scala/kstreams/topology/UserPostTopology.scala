package kstreams.topology

import kstreams.serdes.GenericSerde
import model.{Post, Users, UsersPost}
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.scala.kstream.Joined
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{Consumed, KStream, KTable}
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

      post.join[Users, UsersPost](users)(userPostJoiner)(joinedParams).peek((_, v) => {
        println(s"The joined stream is $v")
      })

    builder.build()
  }
}
