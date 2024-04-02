package http
import cats.effect._,  org.http4s.dsl.io._

import org.http4s.HttpRoutes

object Endpoints {
  def route: HttpRoutes[IO] = {
    HttpRoutes.of[IO] {
      case GET -> Root / "hello" =>
        Ok("Hello 😊")
    }
  }
}
