package kstreams.serdes

import io.circe.{Decoder, Encoder}
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.scala.serialization.Serdes
import io.circe.syntax._

trait GenericSerde {
  def create[T >: Null : Encoder: Decoder] : Serde[T]
}

class CustomSerdes extends GenericSerde {
  def create[T >: Null : Encoder : Decoder]: Serde[T] = {
    Serdes.fromFn((input: T) =>  input.asJson.noSpaces.getBytes(), (input: Array[Byte]) => input.asJson.as[T] match {
        case Left(_) => None
        case Right(value) => Option(value)
      }
    )
  }
}


