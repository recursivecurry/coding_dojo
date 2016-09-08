package algorithm

import scala.collection.BitSet

/**
  * Created by pi on 2016. 8. 25..
  */
class PersistentBookcase(val row: Int, val col: Int) {

  var history: Vector[BitSet] = Vector(BitSet.empty)

  def one(prev: BitSet, i: Int, j: Int): (BitSet, () => Unit) =
    (prev + (col * i + j), () => {println(history.last.size)})

  def main(args: Array[String]): Unit = {

  }
}
