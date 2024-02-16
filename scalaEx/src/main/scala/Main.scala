import traits.{Circle, Rectangle, Shapes, Square}


object Draw {
  def apply(shape: Shapes): Unit = {
    shape match {
      case Circle(radius) => println(s"Circle has radius $radius")
      case Rectangle(length, width) => println(s"Rectangle has width $width and lenght $length")
      case Square(size) => println(s"Square has size $size")
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val shape = Circle(2)

    Draw(shape)
  }
}
