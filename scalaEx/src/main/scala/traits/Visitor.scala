package traits

import java.util.Date

trait Visitor {
  def id: String

  def createdAt: Date

  def age: Long = new Date().getTime - createdAt.getTime
}

case class Anonymous(id: String, createdAt:Date = new Date()) extends Visitor
case class User(id: String, email: String, createdAt:Date = new Date()) extends Visitor