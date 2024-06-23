package service

import cats.effect.kernel.Sync
import model.Name

trait HelloWorld[F[_]] {
  def sayHello(name: Name): F[String]
}

class HelloWorldService[F[_]: Sync] extends HelloWorld[F] {
  override def sayHello(name: Name): F[String] = Sync[F].
    pure(s"Hello ${name.value}")
}