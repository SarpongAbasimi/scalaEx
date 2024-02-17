package traits

sealed trait TrafficLight extends Product with Serializable {
  def next: TrafficLight = this match {
    case Red => Yellow
    case Yellow => Green
    case Green => Red
  }
}

case object Red extends TrafficLight
case object Yellow extends TrafficLight
case object Green extends  TrafficLight
