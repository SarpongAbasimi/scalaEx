package utils

import cats.effect.{IO, IOApp}
import server.Server

object ServerMain extends IOApp.Simple {
  override def run: IO[Unit] = {
    Server.create.use(_ => IO.never[Unit])
  }
}
