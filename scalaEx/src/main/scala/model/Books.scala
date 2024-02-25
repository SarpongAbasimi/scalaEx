package model

trait Books extends Product with Serializable {
  def name: String
  def language: String
  def writer: String
}

final case class Italian(name: String, writer: String, ratings: Int) extends Books {
  override def language: String = "italian"
}

final case class English(name: String, writer: String, ratings: Int) extends Books {
  override def language: String = "eng"
}

final case class Ghanaian(name: String, writer: String, ratings: Int) extends Books {
  override def language: String = "twi"
}


