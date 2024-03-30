package model

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

import java.time.Instant

final case class BodyTemperature(timeStamp: Instant, temperature: Double, unit: String)

object BodyTemperature {
  implicit val encoder: Encoder[BodyTemperature] = deriveEncoder[BodyTemperature]
  implicit val decoder: Decoder[BodyTemperature] = deriveDecoder[BodyTemperature]
}