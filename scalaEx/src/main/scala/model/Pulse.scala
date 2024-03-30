package model

import io.circe.{Decoder, Encoder}

import java.time.Instant
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

final case class Pulse(timeStamp: Instant)

object Pulse {
  implicit val encoder: Encoder[Pulse] = deriveEncoder[Pulse]
  implicit val decoder: Decoder[Pulse] = deriveDecoder[Pulse]
}