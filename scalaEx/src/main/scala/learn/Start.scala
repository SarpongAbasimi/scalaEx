package learn
import cats.effect
import cats.effect.{IO, IOApp}
import cats.effect.kernel.Resource

object Start {
  def addition(a: Int, b:Int) : IO[Int]= {
    IO.pure(a+ b)
  }

  def mrResource(path: String): Resource[IO, Int] =
    effect.Resource.make[IO, Int](IO.pure(1))(res => IO.unit)


  def userResource(input: String): IO[Unit] = mrResource(input).use(res => {
    IO.println(s"The resource is $res")
  })
}


object A extends IOApp.Simple {
  override def run: IO[Unit] = Start.userResource("yello")
}