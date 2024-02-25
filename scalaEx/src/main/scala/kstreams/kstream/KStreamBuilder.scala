package kstreams.kstream

import io.circe.{Decoder, Encoder}
import kstreams.serdes.CustomSerdes
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{Consumed, KStream}
import org.apache.kafka.streams.scala.serialization.Serdes


object KStreamBuilder {

  def create[T: Encoder: Decoder]: KStream[Array[Byte], T] =  {
    val builder: StreamsBuilder = new StreamsBuilder()
     builder.stream[Array[Byte], T]("users")(Consumed.`with`(Serdes.byteArraySerde, new CustomSerdes().create[T]))
  }
}
