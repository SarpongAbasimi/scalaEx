package traits

trait HtmlWriter[A] {
  def toHtml(value: A): String
}



