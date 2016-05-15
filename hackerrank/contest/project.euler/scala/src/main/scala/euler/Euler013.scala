package euler

import scala.io.StdIn

/**
  * Created by jongsoo.lee on 2016. 5. 15..
  */
object Euler013 {


  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val ls = List.tabulate(n)(_ => BigInt(StdIn.readLine))
    println(ls.sum.toString().take(10))
  }
}
