package learn
import cats.effect
import cats.effect.{IO, IOApp}
import cats.effect.kernel.Resource

object Start {
  def addition(a: Int, b:Int) : IO[Int]= {
    IO.pure(a + b)
  }

  def mrResource(path: String): Resource[IO, String] =
    effect.Resource.make[IO, String](IO.pure(path))(_ => IO.unit)


  def userResource(input: String): IO[Unit] = mrResource(input).use(res => {
    IO.println(s"The resource is $res")
  })

  def theMap[A, B](input: A)(f: A => B): B =
    f(input)
}


object A extends IOApp.Simple {
  override def run: IO[Unit] = Start.userResource("yello")
}