package learn

import scala.annotation.tailrec

//sealed trait Human
//
//case object Parent extends Human
//case object Child extends Human
//case class Person(input: Int)
//
//@tailrec
//def loopForMe(input: Int): Int = {
//  if (input == 0) {
//    input
//  } else {
//    println(input)
//    loopForMe(input - 1)
//  }
//}
//
//
//def myFactorialFor(value: Int): Int = {
//  @tailrec
//  def iter(input: Int, res: Int): Int = {
//    if (input == 1) {
//      res
//    } else {
//      iter(input - 1, res * input)
//    }
//  }
//  iter(value, 1)
//}


//enum MyList[+A]:
//  case EmptyList
//  case NonEmptyList(head: A, tail: MyList[A])
//
//  object MyList {
//    def apply[A](input: A*): MyList[A] =
//      if (input.isEmpty) then EmptyList
//      else NonEmptyList(input.head, apply(input.tail *))
//
//    def myListMap[A, B](input: MyList[A])(f: A => B): MyList[B] = {
//      input match {
//        case EmptyList => EmptyList
//        case NonEmptyList(head: A, tail: MyList[A]) => ???
//      }
//    }
//  }


//sealed trait MyStack[+A]
//
//case object EmptyStack extends MyStack[Nothing]
//
//final case class TheRestOfTheStack[A](top: A, rest: MyStack[A]) extends MyStack[A]
//
//object MyStack {
//  def apply[A](input: A*): MyStack[A] = {
//    if (input.isEmpty) {
//      EmptyStack
//    } else {
//      TheRestOfTheStack(input.head, apply(input.tail *))
//    }
//  }
//
//  def push[A](top: A, tail: MyStack[A]): MyStack[A] = {
//    TheRestOfTheStack(top, tail)
//  }
//
//  def pop[A](stack: MyStack[A]): MyStack[A] = stack match {
//    case EmptyStack => EmptyStack
//    case TheRestOfTheStack(_, rest) => rest
//  }
//}








