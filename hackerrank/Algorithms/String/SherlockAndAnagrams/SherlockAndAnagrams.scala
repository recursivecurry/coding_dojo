import scala.io.StdIn

object Solution {
  def main(args: Array[String]) {
    val n = StdIn.readInt()

    for (i <- 0 until n) {
      val input = StdIn.readLine()
      val anagrams = input.tails.toList.flatMap(_.inits.withFilter(!_.isEmpty).toList.map(_.sorted))
      val ans = anagrams.groupBy(identity).values.map(s => s.size * (s.size-1) / 2).sum
      println(ans)
    }
  }
}
