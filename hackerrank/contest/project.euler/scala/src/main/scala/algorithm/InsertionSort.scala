package algorithm

import scala.collection.mutable

/**
  * Created by pi on 2016. 7. 23..
  */
object InsertionSort {

  def square[T](x: T)(implicit num: Numeric[T]): T = {
    import num._
    x * x
  }

  def insertionSort[T: math.Ordering](input: mutable.IndexedSeq[T]): mutable.IndexedSeq[T] =
    if (1 < input.size) {
      var current: Int = 1

      while (current < input.size) {
        var pos: Int = 0
        while (implicitly[Ordering[T]].lt(input(pos), input(current))) {
          pos += 1
        }
        val temp: T = input(current)
        var other: Int = current
        while (pos < other) {
          input(other) = input(other-1)
          other -= 1
        }
        input(pos) = temp
        current += 1
      }
      input
    } else {
      input
    }

  def main(args: Array[String]): Unit = {
    val input1: Array[Int] = Array(0, 0, 1, 1, 3, 3)
    val input2: Array[Int] = Array(3, 3, 1, 1, 0, 0)
    val input3: Array[Int] = Array(3, 0, 1, 3, 0, 1)
//    println(insertSort(input1))
    println(insertionSort(input2))
    println(insertionSort(input3))
  }
}
