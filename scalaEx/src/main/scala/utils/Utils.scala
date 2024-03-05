package utils

import org.apache.kafka.streams.StreamsConfig

import java.util.Properties

object Utils {

  def createProperties(appName: String): Properties = {
    val properties: Properties = new Properties()
    properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(StreamsConfig.APPLICATION_ID_CONFIG, appName)
    properties
  }
}
