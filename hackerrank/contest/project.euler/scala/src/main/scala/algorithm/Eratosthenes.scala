package algorithm

import scala.io.StdIn
import scala.math

/**
  * Created by pi on 2016. 7. 24..
  */
object Eratosthenes {
  val sieve: Array[Int] = Array.fill(Int.MaxValue / 32 + 1)(Int.MaxValue)

  def isPrime(n: Int): Boolean = {
    (sieve(n >> 5) & (1 << (n & 31))) != 0
  }

  def setComposite(n: Int): Unit = {
    sieve(n >> 5) = sieve(n >> 5) & ~(1 << (n & 31))
  }

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val sqrtn = math.sqrt(n)

    setComposite(0)
    setComposite(1)

    var i = 2
    while (i < sqrtn) {
      if (isPrime(i)) {
        var nonPrime = i + i
        while (nonPrime < n) {
          setComposite(nonPrime)
          nonPrime += i
        }
      }
      i += 1
    }
    i = 0
    while (i < n) {
      if (isPrime(i)) {
        println(i)
      }
      i += 1
    }
  }
}
