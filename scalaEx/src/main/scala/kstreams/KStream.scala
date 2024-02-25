package kstreams

import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}
import org.apache.kafka.streams.kstream.KStream

import java.util.Properties

object KStream {
  val builder: StreamsBuilder = new StreamsBuilder()
  val topic: String = "users"
  val stream: KStream[Void, String] = builder.stream[Void, String](topic)

  stream.foreach((_, value) => {
    println(s"The value is $value")
  })

  val settings = new Properties();
  // Set a few key parameters
  settings.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-first-streams-application");
  settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker1:9092");

  val kafkaStream = new KafkaStreams(builder.build(), settings)
}
