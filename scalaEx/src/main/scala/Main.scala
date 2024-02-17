import traits.{Circle, Empty, IntList, Pair, Rectangle, Red, Shapes, Square, Tigers}

import scala.annotation.tailrec


object Draw {
  def apply(shape: Shapes): Unit = {
    shape match {
      case Circle(radius) => println(s"Circle has radius $radius")
      case Rectangle(length, width) => println(s"Rectangle has width $width and lenght $length")
      case Square(size) => println(s"Square has size $size")
    }
  }
}

object Calc {
  @tailrec
  def sum(list: IntList, accumulator: Int = 0): Int = list match {
    case Empty => accumulator
    case Pair(head, tail) => sum(tail, head + accumulator)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val list = Pair(1, Pair(3, Empty))
    val result = Calc.sum(list)

    println(result)
  }
}
