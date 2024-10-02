package algo

object Start {
  def myBinarySearch(searrchItem: Int,  guessList: List[Int]):Int = {
    val low = 0
    val high = guessList.length - 1


    def go(low: Int, high: Int): Int = {
      val mid = (low + high) / 2
      val midGuess = guessList(mid)

     if (midGuess == searrchItem) {
        midGuess
      } else {
        if (midGuess < searrchItem) {
          go(midGuess + 1, high)
        } else {
          go(low - 1, midGuess)
        }
      }
    }
    if(guessList.isEmpty){
      0
    } else go(low, high)
  }

  def f(delim:Int,arr:List[Int]) = {
    arr.filter(element => element < delim).foreach(println)
  }


  def fizzBuzz(n: Int): Unit = {
    val range: Seq[Int] = Range.inclusive(1, n)


    def isMultiple(value: Int, multiple: Int): Boolean = {
      value % multiple == 0
    }

    range.foreach(value => {
      (isMultiple(value, 3),isMultiple(value, 5)) match {
        case (true, true) =>  println("FizzBuzz")
        case (true, false) => println("Fizz")
        case (false, true) => println("Buzz")
        case _ =>  println(value)
      }
    })
  }

  val request  = Array(
    "https://api.example.com/resource6/resource21/",
    "https://api.example.com/resource951/id60/resource115/",
    "https://api.example.com/resource951/id60/resource115/",
    "https://api.example.com/resource535/id52/id57/id53/",
    "https://api.example.com/resource535/id52/id57/id53/"
  )

  def getResourceList(requests: Array[String]) = {


    val myRegex = "(\\/id\\w*)"
     requests.toList.distinct.
      flatMap(_.split("https://api.example.com/"))
      .filterNot(_.isEmpty)
      .map(_.replaceAll(myRegex, ""))
      .sortBy(element => {
        element.count(_ == '/')
      })
      .map(_.dropRight(1))


  }

  def main(args: Array[String]): Unit = {
    val results = getResourceList(request)

    println(results)
  }
}
