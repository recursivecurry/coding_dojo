import scala.io.StdIn
    
object Euler018 {

  def update(ps: collection.immutable.IndexedSeq[Int], ns: collection.immutable.IndexedSeq[Int]): collection.immutable.IndexedSeq[Int] = {
    for (n <- ns.zipWithIndex) yield
      if (n._2==0)
        ps(0) + n._1
      else if (n._2==ns.length-1)
        ps(n._2-1) + n._1
      else
        ps(n._2).max(ps(n._2-1)) + n._1
  }

  def test(): Unit = {
    val n = StdIn.readInt()
    val tree = for (i <- 0 until n) yield StdIn.readLine().split(" ").map(_.toInt).toIndexedSeq
    println(tree.tail.foldLeft(tree.head)(update).max)
  }

  def main(args: Array[String]) {
    val t = StdIn.readInt()
    for (_ <- 0 until t) {
      test()
    }
  }
}
