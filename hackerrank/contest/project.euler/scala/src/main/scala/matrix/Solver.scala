package matrix

import scala.annotation.tailrec

/**
  * Created by pi on 2016. 8. 21..
  */
object Solver {

  def solve[T](rowList: List[Map[T,Int]], labelList: List[T], b: List[Int]): Map[T,Int] = {

    @tailrec
    def solveIter[T](rowList: List[Map[T,Int]], labelList: List[T], rightList: List[Int], answer: Map[T,Int]): Map[T,Int] = labelList match {
      case Nil => answer
      case _ =>
        val current = labelList.head
        val right = rightList.head
        val leftList = rowList.head
        val otherSum = answer.foldLeft(0)((acc, a) => acc + leftList(a._1) * a._2)
        solveIter(rowList.tail, labelList.tail, rightList.tail, answer + (current -> (right - otherSum) / leftList(current)))
    }

    solveIter(rowList.reverse, labelList.reverse, b.reverse, Map.empty)
  }

  def main(args: Array[String]): Unit = {
    val rowList = List(Map('x' -> 3, 'y' -> 2, 'z' -> 1), Map('y' -> 3, 'z' -> 2), Map('z' -> 3))
    val labelList = List ('x', 'y', 'z')
    val b = List(11, 14, 12)
    print(solve(rowList, labelList, b))
  }

}
