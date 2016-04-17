package euler

import scala.annotation.tailrec
import scala.io.StdIn

/**
  * Created by jongsoo.lee on 2016. 4. 17..
  */
object Euler012 {

  def numberOfDivisor(number: Int): Int = {
    @tailrec
    def numberOfDivosorTail(number: Int, count: Int, divisor: Int = 2): Int = {
      number match {
        case 1 => count
        case number if number % divisor == 0 => numberOfDivosorTail(number / divisor, count+1, divisor)
        case _ => numberOfDivosorTail(number, count, if (divisor==2) 3 else divisor + 2)
      }
    }
    val ans = numberOfDivosorTail(number, 1, 2)
    ans
  }

  def nthTriangleNumber(n: Int): Int = n * (n+1) / 2

  def numberOfDivisorOfnthTriangleNum(n: Int): Int = {
    if (n % 2 == 0)
      numberOfDivisor(n/2) * numberOfDivisor(n+1)
    else
      numberOfDivisor(n) * numberOfDivisor((n+1)/2)
  }

  def solve(minimum: Int): Int = {
    Stream.from(1).find(minimum < numberOfDivisorOfnthTriangleNum(_)).map(nthTriangleNumber).get
  }

  def main(args: Array[String]): Unit = {
    val T = StdIn.readInt()
    (0 until T).foreach((_) => println(solve(StdIn.readInt())))
  }
}
