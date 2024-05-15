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

  def main(args: Array[String]): Unit = {
    val f = myBinarySearch(10,  List(1,2,3,4,5,6,7,8,9,10))
    println(f)

  }
}
