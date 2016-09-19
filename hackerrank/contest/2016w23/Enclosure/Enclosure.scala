import scala.io.StdIn

object Enclosure {

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val ls = StdIn.readLine().split(" ").map(_.toDouble)

    val r = solve(ls)

    val head = ls.head
    val ls2 = ls.tail.init

    println("%.6f\n%.6f\n".format(0.0,0.0))
    println("%.6f\n%.6f\n".format(0.0,head))

    val xDiff: Double = math.tan(math.acos((head/2.0)/r)) * (head / 2.0)
    val yDiff: Double = head / 2.0

    ls2
      .scanLeft((-xDiff, head-yDiff))((prev, l) => {
        val angle = math.asin((l/2)/r) * 2.0
        (math.cos(-angle) * prev._1 - math.sin(-angle) * prev._2, math.sin(-angle) * prev._1 + math.cos(-angle) * prev._2)
      })
      .tail
      .foreach(p => println("%.6f\n%.6f\n".format(p._1 + xDiff, p._2 + yDiff)))
  }

  def solve(ls: Array[Double]): Double = {

    var min: Double = 0
    var max: Double = 1.6777216E7
    var diff: Double = Double.MaxValue
    var r: Double = 0
    var count = 0

    while (0.000000000000001 < math.abs(diff)) {
      r = (min + max) / 2
      val myPi = ls.map(l => math.asin((l/2)/r)).sum
      diff = myPi - math.Pi
      if (diff < 0)
        max = r
      else if ( diff > 0)
        min = r
      count += 1
    }
    r
  }

}

