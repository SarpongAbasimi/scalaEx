package traits

sealed trait TrafficLight extends Product with Serializable

case object Red extends TrafficLight
case object Yellow extends TrafficLight
case object Green extends  TrafficLight
