package learn

import weaver.{IOSuite, SimpleIOSuite}

object StartSuite extends SimpleIOSuite{

  test("Should be able to add two numbers" ) { _ =>
    Start.addition(1,2).map(res => expect(res == 3))
  }
}
