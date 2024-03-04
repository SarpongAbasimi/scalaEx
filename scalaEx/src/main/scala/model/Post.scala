package model

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

final case class Post(id: Int, title: String, body: String, userId: Int)

object Post {
  implicit val encoder: Encoder[Post] = deriveEncoder
  implicit val decoder: Decoder[Post] = deriveDecoder
}