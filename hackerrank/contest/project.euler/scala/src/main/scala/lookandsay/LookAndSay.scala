package lookandsay

import scala.annotation.tailrec
import scala.io.StdIn

/**
  * Created by pi on 2016. 7. 23..
  */
object LookAndSay {

  def lookAndSay(n: Int): List[Int] = n match {
    case 1 => List(1)
    case n => {
      def lookAndSayRec(xss: List[Int]): List[Int] = xss match {
        case Nil => Nil
        case (x :: xs) => {
          val (heads, others) = xss.span(i => i == x)
          heads.length :: x :: lookAndSayRec(others)
        }
      }
      lookAndSayRec(lookAndSay(n-1))
    }
  }

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    println(lookAndSay(n))
  }
}
