package traits

sealed trait Shapes {
  def sides: Int
  def perimeter: Double
  def area: Double
}

final case class Circle(radius: Double) extends Shapes {
  def sides: Int = 4
  def perimeter: Double = 2 * math.Pi * radius
  def area: Double = math.Pi * radius * radius
}

final case class Rectangle(length: Double, width: Double) extends Shapes {
  def sides: Int = 4
  def perimeter: Double = 2 * (length + width)
  def area: Double = width * length
}

final case class Square(size: Double) extends Shapes {
  def sides: Int = 4
  def perimeter: Double = 4 * size
  def area: Double = size * size
}

