package model
import io.circe.{Decoder, Encoder}
import io.circe.generic.auto._
import io.circe.syntax._
import cats.syntax.functor._
sealed trait Books extends Product with Serializable {
  def name: String
  def language: String
  def author: String
}

final case class Italian(name: String, author: String, ratings: Int) extends Books {
  override def language: String = "italian"
}

final case class English(name: String, author: String, ratings: Int) extends Books {
  override def language: String = "eng"
}

final case class Ghanaian(name: String, author: String, ratings: Int) extends Books {
  override def language: String = "twi"
}

object Books {
  implicit val encodeBooks: Encoder[Books] = Encoder.instance {
    case  ita@Italian(_, _, _) => ita.asJson
    case  eng@English(_, _, _) => eng.asJson
    case  gh@Ghanaian(_,_,_) => gh.asJson
  }

  implicit val decodeBooks: Decoder[Books] =
    List[Decoder[Books]](
      Decoder[Italian].widen,
      Decoder[English].widen,
      Decoder[Ghanaian].widen
    ).reduceLeft(_ or _)
}
