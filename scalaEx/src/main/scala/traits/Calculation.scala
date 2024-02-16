package traits

sealed trait Calculation

final case class Success(result: Int) extends Calculation
final case class Failed(message: String) extends Calculation
