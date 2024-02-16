package traits

sealed trait Feline {
  def colour: String
  def sound: String
  def dinner: Food = this match {
    case Tigers(_) => TigerFood
    case Lions(_, _) => LionFood
    case Panther(_) => PantherFood
    case Cat(_, _) => CatFood
  }
}

final case class Tigers(colour: String) extends Feline {
   def sound: String = "roar"
}
final case class Lions(colour: String, manSize: Int) extends Feline {
  def sound: String = "roar"
}
final case class Panther(colour: String) extends Feline {
  def sound: String = "roar"
}

final case class Cat(colour: String, favFood: String) extends Feline {
  override def sound: String = "meow"
}