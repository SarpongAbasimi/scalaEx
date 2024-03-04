package kstreams.topology

import kstreams.serdes.GenericSerde
import model.{Post, Users, UsersPost}
import org.apache.kafka.streams.scala.kstream.Joined
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{Consumed, KStream, KTable}
import org.apache.kafka.streams.scala.serialization.Serdes

class UserPostMain(customSerdes: GenericSerde) {

  val builder = new StreamsBuilder()

  val users: KTable[Int, Users] = builder.table[Int, Users]("users")(Consumed.`with`[Int, Users](
    Serdes.intSerde, customSerdes.create[Users]))

  val post: KStream[Int, Post] = builder.stream[String, Post]("post")(Consumed.`with`[String, Post](
      Serdes.stringSerde, customSerdes.create[Post]))
    .selectKey((_, post) => post.userId)

  val joinedParams: Joined[Int, Post, Users] = Joined
    .`with`[Int, Post, Users](
      Serdes.intSerde,
      customSerdes.create[Post],
      customSerdes.create[Users]
    )

  val userPostJoiner: (Post, Users) => UsersPost = (p, u) => UsersPost(u.id, u.name, u.email, p.title, p.id)

  post.join[Users, UsersPost](users)(userPostJoiner)(joinedParams)
}
