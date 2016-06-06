import scala.annotation.tailrec
import scala.collection.immutable.TreeMap
import scala.io.StdIn
    
object Solution {
  @tailrec
  def readOperation(abks: IndexedSeq[String], operations: TreeMap[Long,Long]): TreeMap[Long,Long] = {
    if (abks.isEmpty)
      operations
    else {
      val abk = abks.head.split("\\s+").map(_.toLong).toVector
      val from = abk(0) -> (operations.getOrElse(abk(0), 0L) + abk(2))
      val to = abk(1)+1 -> (operations.getOrElse(abk(1)+1, 0L) - abk(2))
      readOperation(abks.tail, operations + from + to)
    }
  }

  def main(args: Array[String]): Unit = {
    val nm = StdIn.readLine().split("\\s+").map(_.toInt).toVector
    val abks = for (_ <- 0 until nm(1)) yield StdIn.readLine()
    val operations = readOperation(abks, TreeMap.empty)

    println(operations.values.scanLeft(0L)(_+_).max)
  }
}
