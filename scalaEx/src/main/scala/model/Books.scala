package model
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

final case class Books(id: Int, title: String, lang: String, goodRating: Boolean, randomData: String = "")

object Books {
  implicit val encoder: Encoder[Books] = deriveEncoder[Books]
  implicit val decoder: Decoder[Books] = deriveDecoder[Books]
}
