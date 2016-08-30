package algorithm

import scala.io.StdIn

object Solution {

  def solve(ns: Seq[(Long,Int)], begin: Int, end: Int, target: Long, total: Long): Int =
    partition(ns, begin, end, target, total) match {
      case None => 0
      case Some(m) => 1 + (solve(ns, begin, m - 1, target - total / 4, total / 2) max solve(ns, m, end, target + total / 4, total / 2))
    }

  def partition(ns: Seq[(Long,Int)], begin: Int, end: Int, target: Long, total: Long): Option[Int] = {
    val length = end - begin + 1
    if (total % 2 == 1 || length <= 1)
      None
    else {
      ns.slice(begin, begin + length).find(_._1 ==  target).map(_._2)
    }
  }

  def main(args: Array[String]): Unit = {
    val n: Int = StdIn.readInt()
    for (_ <- 0 until n) {
      StdIn.readInt()
      val ns: Seq[(Long,Int)] = StdIn.readLine().split(" ").map(_.toLong).scanLeft(0L)(_ + _).zipWithIndex.tail
      println(solve(ns, 0, ns.length-1, ns.last._1 / 2, ns.last._1))
    }
  }
}

