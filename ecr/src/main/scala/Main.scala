import cats.effect.{IO, IOApp}
import model.Name
import service.HelloWorld
import service.HelloWorldService

object Main  extends IOApp.Simple {
  override def run: IO[Unit] = {
    val name = Name("Ben")

    val helloWorldService: HelloWorld[IO] = new HelloWorldService[IO]

    helloWorldService.sayHello(name)
      .flatMap(IO.println(_))
  }
}
