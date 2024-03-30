package kstreams.topology

import kstreams.serdes.CustomSerdes
import model.{BodyTemperature, Pulse}
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.{Printed, TimeWindows, Windowed}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{Consumed, Grouped, KStream, KTable, Materialized}
import org.apache.kafka.streams.scala.serialization.Serdes

import java.time.Duration

class WindowTopology(customSerdes: CustomSerdes) {

  def create: Topology = {
    val streamBuilder: StreamsBuilder = new StreamsBuilder()

    val pulseEvents: KStream[String, Pulse] = streamBuilder
      .stream("pulse-event")(Consumed.`with`[String, Pulse](Serdes.stringSerde, customSerdes.create[Pulse]))

    val bodyTemperature: KStream[String, BodyTemperature] = streamBuilder
      .stream("body-temp-event")(Consumed.`with`[String, BodyTemperature](Serdes.stringSerde, customSerdes.create[BodyTemperature]))

    val pulseCount: KTable[Windowed[String], Long] = pulseEvents.groupByKey(Grouped.`with`(Serdes.stringSerde, customSerdes.create[Pulse]))
      .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(60)))
      .count()(Materialized.as("pulse-count")(Serdes.stringSerde, Serdes.longSerde))

    pulseCount
      .toStream
      .print(
        Printed.toSysOut[Windowed[String], Long]
          .withLabel("pulse-count")
      )

    streamBuilder.build()
  }

}
