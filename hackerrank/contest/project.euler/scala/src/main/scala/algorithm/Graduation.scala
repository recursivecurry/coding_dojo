package algorithm

import scala.io.StdIn
import scala.collection.breakOut

/**
  * Created by pi on 2016. 7. 26..
  */
object Graduation {

  def solve(t: Int, n: Int, k: Int, m: Int, l: Int, courseMap: Map[Int,Array[Int]], semesters: Map[Int,Array[Int]]): Int = {
    print(t, n, k, m, l, courseMap, semesters)
    0
  }

  def main(args: Array[String]): Unit = {
    val t = StdIn.readLine().toInt

    val Array(n, k, m, l) = StdIn.readLine().split(" ").map(_.toInt)

    val courseMap: Map[Int,Array[Int]] = (for {
      i <- 0 until n
      dependencies: Array[Int] = StdIn.readLine().split(" ").drop(1).map(_.toInt)
    } yield i -> dependencies)(breakOut)

    val semesters: Map[Int,Array[Int]] = (for {
      i <- 0 until m
      openCourses: Array[Int] = StdIn.readLine().split(" ").drop(1).map(_.toInt)
    } yield i -> openCourses)(breakOut)

    println(solve(t, n, k, m, l, courseMap, semesters))
  }

}
