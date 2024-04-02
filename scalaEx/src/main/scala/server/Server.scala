package server
import cats.effect.{IO, Resource}
import com.comcast.ip4s._
import http.Endpoints
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server

object Server {
  def create: Resource[IO, Server] = {
    EmberServerBuilder.default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(Endpoints.route.orNotFound)
      .build
  }
}
