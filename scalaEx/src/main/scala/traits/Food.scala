package traits

sealed trait Food extends Product with Serializable

case object LionFood extends Food
case object TigerFood extends Food
case object PantherFood extends Food
case object CatFood extends Food
