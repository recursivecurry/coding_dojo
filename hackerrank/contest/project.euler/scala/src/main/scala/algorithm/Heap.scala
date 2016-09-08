package algorithm

import scala.annotation.tailrec
import scala.collection._

/**
  * Created by pi on 2016. 7. 28..
  */
class Heap[T : math.Ordering](var value: mutable.IndexedSeq[T]) {

  @tailrec
  final def maxHeapify(pos: Int, size: Int = value.size): Unit = {
    val left = pos * 2
    val right = pos * 2 + 1
    val candidate = Array(if (left <= size) left else pos, if (right <= size) right else pos, pos).maxBy(i => value(i - 1))

    if (candidate != pos) {
      val temp = value(candidate-1)
      value(candidate-1) = value(pos-1)
      value(pos-1) = temp
      maxHeapify(candidate, size)
    }
  }

  def buildMaxHeap(size: Int = value.size): Unit = {
    for (i <- size / 2 to 1 by -1) {
      maxHeapify(i, size)
    }
  }

  def heapSort(): Unit = {
    @tailrec
    def heapSortIter(size: Int = value.size): Unit = {
      if (1 < size) {
        val temp = value(size-1)
        value(size-1) = value(0)
        value(0) = temp
        maxHeapify(1, size-1)
        heapSortIter(size-1)
      }
    }
    buildMaxHeap()
    heapSortIter(value.size)
  }
}

object Heap {
  def main(args: Array[String]): Unit = {
    val input0 = Array(5, 3, 9, 1, 4, 7)
    val h0 = new Heap(input0)
    println(h0)
    h0.heapSort()
    println(h0)
  }
}
