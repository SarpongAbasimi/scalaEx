package traits

trait Source extends Product with  Serializable
case object Spring extends Source
case object Well extends Source
case object Tap extends Source

case class BottledWater(size: Int, source: Source, isCarbonated: Boolean)

