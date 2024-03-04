package model

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

final case class Users(id: Int, name: String, email: String)

object Users {
  implicit val encoder: Encoder[Users] = deriveEncoder[Users]
  implicit val decoder: Decoder[Users] = deriveDecoder[Users]
}