package leetcode

import scala.io.StdIn

/**
  * Created by pi on 2016. 8. 2..
  */
object JumpGame {

  def solve(ds: List[Int]): Boolean =
    ds.init.foldLeft[Option[Int]](Some(0))(
      (remained, jump) => remained.flatMap(
        r => if (r == 0 && jump == 0) None else if (r < jump) Some(jump - 1) else Some(r - 1))).isDefined

  def main(args: Array[String]): Unit = {
    val jumps: List[Int] = StdIn.readLine().split(" ").map(_.toInt).toList
    println(solve(jumps).toString)
  }
}
