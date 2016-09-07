// HACKERRANK: Mandragora Forest
// https://www.hackerrank.com/challenges/mandragora

import scala.annotation.tailrec
import scala.collection.mutable
import scala.io.StdIn

object Mandragora {

  @tailrec
  def solve(n: Int, s: Long, hs: mutable.PriorityQueue[Int]): Long = {

    val eatCase: Long = (n+1) * (s)
    if (hs.isEmpty)
      eatCase
    else {
      val current = hs.dequeue()
      val fightCase: Long = n * (s+current)

      if (eatCase < fightCase)
        solve(n-1, s+current, hs)
      else
        eatCase
    }
  }

  def main(args: Array[String]): Unit = {
    val t = StdIn.readInt()

    for (_ <- 0 until t) {
      val n = StdIn.readInt()
      val hs: mutable.PriorityQueue[Int] = mutable.PriorityQueue(StdIn.readLine().split(" ").map(_.toInt): _*)
      println(solve(n, 0L, hs))
    }
  }
}
