import scala.io.StdIn
import scala.math.BigInt

object Solution {

  val cache: Array[BigInt] = Array.fill(21)(-1)

  def fibonacci(t1: BigInt, t2: BigInt, n: Int): BigInt = n match {
    case 1 => t1
    case 2 => t2
    case _ => if (cache(n) != -1) {
      cache(n)
    } else {
      cache(n) = fibonacci(t1, t2, n - 2) + fibonacci(t1, t2, n - 1) * fibonacci(t1, t2, n - 1)
      cache(n)
    }
  }

  def main(args: Array[String]): Unit = {
    val Array(t1,t2,n) = StdIn.readLine().split(" ").map(_.toInt)
    cache(1) = t1
    cache(2) = t2
    println(fibonacci(t1, t2, n))
  }
}
