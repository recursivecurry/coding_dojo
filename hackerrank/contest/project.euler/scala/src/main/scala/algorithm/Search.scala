package algorithm

import scala.annotation.tailrec
import scala.io.StdIn

/**
  * Created by pi on 2016. 7. 27..
  */
object Search {

  def bisectLeft[T: math.Ordering](xs: IndexedSeq[T], n: T): Int = {

    @tailrec
    def bisectLeftIter[S: math.Ordering](xs: IndexedSeq[S], n: S, begin: Int, end: Int): Int =
      if (begin < end) {
        val mid = (begin + end) / 2
        if (implicitly[math.Ordering[S]].compare(n, xs(mid)) <= 0) {
          bisectLeftIter(xs, n, begin, mid)
        } else {
          bisectLeftIter(xs, n, mid+1, end)
        }
      } else {
        begin
      }

    bisectLeftIter(xs, n, 0, xs.size)
  }

  def bisectRight[T: math.Ordering](xs: IndexedSeq[T], n: T): Int = {

    @tailrec
    def bisectRightIter[S](xs: IndexedSeq[S], n: S, begin: Int, end: Int)(implicit ord: math.Ordering[S]): Int =
      if (begin < end) {
        val mid = (begin + end) / 2
        if (implicitly[math.Ordering[S]].compare(n, xs(mid)) < 0) {
          bisectRightIter(xs, n, begin, mid)
        } else {
          bisectRightIter(xs, n, mid+1, end)
        }
      } else {
        begin
      }

    bisectRightIter(xs, n, 0, xs.size)
  }

  def main(args: Array[String]): Unit = {
    val xs = Array(1, 1, 1, 2, 2, 3, 5, 5, 5, 6)
    println(bisectLeft(xs, 0))
    println(bisectLeft(xs, 1))
    println(bisectLeft(xs, 2))
    println(bisectLeft(xs, 4))
    println(bisectLeft(xs, 7))
    println(bisectRight(xs, 0))
    println(bisectRight(xs, 1))
    println(bisectRight(xs, 2))
    println(bisectRight(xs, 4))
    println(bisectRight(xs, 7))
  }

}
