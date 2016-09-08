package algorithm

import scala.collection.mutable

object NetworkFlow {

  case object Done extends Exception {}

  def maxFlow(capacity: Array[Array[Int]], flow: Array[Array[Int]], size: Int): Int = {

    var totalFlow: Int = 0
    val parents: Array[Int] = Array.fill(size)(0)
    val candidates: mutable.Queue[Int] = mutable.Queue()

    try {
      while (true) {
        parents(0) = 0
        candidates += 0

        while (candidates.nonEmpty) {
          val here = candidates.dequeue()
          for (there <- 0 until size) {
            if (capacity(here)(there) - flow(here)(there) > 0
              && parents(there) == -1) {
              candidates.enqueue(there)
              parents(there) = here
            }
          }
        }

        if (parents(size-1) == -1) {
          throw Done
        }

        var amount: Int = Int.MaxValue
        var p: Int = size - 1
        while (p != 0) {
          amount = (capacity(parents(p))(p) - flow(parents(p))(p)).min(amount)
          p = parents(p)
        }

        p = size - 1
        while (p != 0) {
          flow(parents(p))(p) += amount
          flow(p)(parents(p)) += amount
          p = parents(p)
        }

        totalFlow += amount
      }
    } catch {
      case Done =>
    }

    totalFlow
  }

  def main(args: Array[String]): Unit = {
    val size = 100
    val capacity: Array[Array[Int]] = Array.fill(size, size)(0)
    val flow: Array[Array[Int]] = Array.fill(size, size)(0)
    println(maxFlow(capacity, flow, size))
  }
}
