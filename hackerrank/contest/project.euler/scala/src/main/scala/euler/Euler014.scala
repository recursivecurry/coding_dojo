package euler

import scala.math.max

/**
  * Created by jongsoo.lee on 2016. 5. 18..
  */
object Euler014 {

  val MAX = 50
  val termsList = (1 to MAX).map((n) => () => terms(n))

  def terms(n: Int): Int = {

    lazy val ans = if (n == 1)
      1
    else if (n % 2 == 0) {
      val next = n / 2
      if (MAX < next)
        terms(next) + 1
      else
        termsList(next - 1)() + 1
    } else {
      val next = n * 3 + 1
      if (MAX < next)
        terms(next) + 1
      else
        termsList(next - 1)() + 1
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(termsList(0))
    val maxList = termsList.scanLeft(0)((p,c) => max(p, c()))
    println(maxList(10))
    println(maxList(15))
    println(maxList(20))
  }
}
