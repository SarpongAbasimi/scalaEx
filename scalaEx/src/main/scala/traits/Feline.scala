package traits

sealed trait Feline {
  def colour: String
  def sound: String = "roar"
}

final case class Tigers(colour: String) extends Feline
final case class Lions(colour: String, manSize: Int) extends Feline
final case class Panther(colour: String)
final case class Cat(colour: String, favFood: String) extends Feline{
  override def sound: String = "meow"
}