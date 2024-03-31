package kstreams.topology

import kstreams.serdes.CustomSerdes
import model.{BodyTemperature, Pulse}
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Suppressed.BufferConfig
import org.apache.kafka.streams.kstream.{Printed, Suppressed, TimeWindows, Windowed}
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
      .windowedBy(TimeWindows.ofSizeAndGrace(Duration.ofSeconds(5), Duration.ofSeconds(2)))
      .count()(Materialized.as("pulse-count")(Serdes.stringSerde, Serdes.longSerde))
      .suppress(Suppressed.untilWindowCloses(BufferConfig.unbounded().shutDownWhenFull()))

    pulseCount
      .toStream
      .print(
        Printed.toSysOut[Windowed[String], Long]
          .withLabel("pulse-count")
      )

    streamBuilder.build()
  }

}
