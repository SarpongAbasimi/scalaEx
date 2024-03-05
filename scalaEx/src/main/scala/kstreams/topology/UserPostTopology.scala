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

  val users: KTable[Int, Users] = builder.table[Int, Users]("users")(Consumed.`with`[Int, Users](
    Serdes.intSerde, customSerdes.create[Users]))

    val post: KStream[Int, Post] = builder.stream[Int, Post]("post")(Consumed.`with`[Int, Post](
        Serdes.intSerde, customSerdes.create[Post]))
      .selectKey((_, post) => post.userId)
      .peek((_,b) => println(s"$b"))

    val joinedParams: Joined[Int, Post, Users] = Joined
      .`with`[Int, Post, Users](
        Serdes.intSerde,
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
