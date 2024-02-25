package learn

import model.Person
import traits._

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

  def len(list: IntList, size: Int = 0): Int = {
    list match {
      case Empty => size
      case Pair(_, tail) => {
        println(tail)
        len(tail, size + 1)
      }
    }
  }
}

object Writer {
    val htmlWriter = new HtmlWriter[Person] {
      def toHtml(value: Person): String = {
        s"The person is ${value.name} and age is ${value.age}"
      }
    }
}

object PersonWriter extends HtmlWriter[Person]{
  override def toHtml(value: Person): String = s"This is ${value.name} typeClass"
}

object MyImp {
  implicit class IntEnrichment(value: Int) {
    def yeah(): Unit = {
      for(_ <- 0 until value){
        println(s"Oh yeah")
      }
    }
  }
}