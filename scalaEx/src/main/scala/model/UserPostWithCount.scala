package model

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

final case class UserPostWithCount() {
  def create(usersPost: UsersPost): UserPostWithCount = {
    val newUserPost = usersPost.copy(count = usersPost.count + 1)
    println(newUserPost)
    this
  }
}

object UserPostWithCount {
  implicit val encoder: Encoder[UserPostWithCount] = deriveEncoder[UserPostWithCount]
  implicit val decoder: Decoder[UserPostWithCount] = deriveDecoder[UserPostWithCount]
}
