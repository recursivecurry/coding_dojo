import scala.io.StdIn
import scala.math

object Lighthouse {

  case class Grid(row: Int, col: Int, empty: Boolean)

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()

    val (empty, obstructed) =
      (for (r <- 0 until n)
        yield StdIn.readLine().zipWithIndex.map(c => if (c._1 == '.') Grid(r, c._2, true) else Grid(r, c._2, false))
        ).flatten.partition(_.empty)

    val top = for (i <- -1 to n) yield Grid(-1, i, false)
    val bottom = for (i <- -1 to n) yield Grid(n, i, false)
    val left = for (i <- -1 to n) yield Grid(i, -1, false)
    val right = for (i <- -1 to n) yield Grid(i, n, false)

    val allObstructed = Seq(obstructed, top, bottom, left, right).flatten

    val radius =
      (for (e <- empty)
        yield allObstructed.map(g => math.sqrt(math.pow(g.row - e.row, 2) + math.pow(g.col - e.col, 2))).min
        ).max
    println(if (radius.toInt.toDouble == radius) radius.toInt - 1 else radius.toInt)
  }
}
