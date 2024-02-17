package traits

sealed trait IntList
case object Empty extends IntList
case class Pair(head: Int, tail: IntList) extends IntList
