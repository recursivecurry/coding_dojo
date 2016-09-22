import scala.io.StdIn

object Euler016 {
  def main(args: Array[String]): Unit = {
    var n: Int = StdIn.readInt()
    while (0 < n) {
      val charArray = String.valueOf(BigInt(2).pow(StdIn.readInt())).toCharArray

      val ans: Int = charArray
        .map(_.toInt - '0'.toInt)
        .sum
      println(ans)
      n -= 1
    }
  }
}
