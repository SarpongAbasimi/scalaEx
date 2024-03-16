package model

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

final case class UsersPost(
                            id: Int,
                            name: String,
                            email: String,
                            title: String,
                            postId: Int,
                            count: Int = 0
                          )

object UsersPost {
  implicit val encoder: Encoder[UsersPost] = deriveEncoder[UsersPost]
  implicit val decoder: Decoder[UsersPost] = deriveDecoder[UsersPost]
}
