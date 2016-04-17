package euler

import scala.annotation.tailrec
import scala.io.StdIn

object Euler012 {

  def numberOfDivisor(number: Long): Long = {
    @tailrec
    def numberOfDivosorTail(number: Long, divisors: Long, divisor: Long, count: Long): Long = {
      number match {
        case 1L => divisors * (count + 1)
        case number if number % divisor == 0 => numberOfDivosorTail(number / divisor, divisors, divisor, count + 1)
        case _ => numberOfDivosorTail(number, divisors * (count + 1), if (divisor==2L) 3L else divisor + 2L, 0)
      }
    }
    numberOfDivosorTail(number, 1L, 2L, 0L)
  }

  def nthTriangleNumber(n: Long): Long = n * (n+1L) / 2L

  def solve(minimum: Long): Long = {
    @tailrec
    def solve2Tail(n: Long, lnum: Long, rnum: Long): Long = {
      if (minimum < lnum * rnum) {
        n
      } else {
        if (n % 2 == 0)
          solve2Tail(n+1, rnum, numberOfDivisor((n+2)/2))
        else
          solve2Tail(n+1, rnum, numberOfDivisor(n+2))
      }
    }
    nthTriangleNumber(solve2Tail(1, 1, 1))
  }

  def main(args: Array[String]): Unit = {
    val T = StdIn.readInt()
    (0 until T).foreach((_) => println(solve(StdIn.readLong())))
  }
}
