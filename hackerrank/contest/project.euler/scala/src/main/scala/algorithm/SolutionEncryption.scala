package algorithm

import scala.io.StdIn

/**
  * Created by pi on 2016. 8. 27..
  */
object SolutionEncryption {

//  def transpose(rows: Seq[String]):
  def main(args: Array[String]): Unit = {

    val s = StdIn.readLine()
    val cols: Int = math.sqrt(s.length).ceil.toInt

    println(StdIn.readLine().toArray.grouped(cols).toArray.transpose.map(_.mkString).mkString(" "))
  }
}
