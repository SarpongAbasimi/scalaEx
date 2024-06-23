package service

import model.Name
import weaver.SimpleIOSuite

object HelloWorldTest extends SimpleIOSuite{
  private val helloWorldService = new HelloWorldService
  private val createName: String => Name = input => Name(input)

  test(s"that sayHello when given a name says Hello name"){

    helloWorldService.sayHello(createName("mao"))
      .map(result =>  expect(result == "Hello mao"))
  }
}
