import scala.io.StdIn
import scala.collection.{breakOut, mutable}

object SynchronousShopping {

  var n, m, k: Int = _

  var fishMap: Array[Int] = _
  var roads: Array[mutable.Map[Int,Int]] = _
  var paths: Array[mutable.Map[Int,Int]] = _

  def buildAllPath(): Unit = {
    val queue = new mutable.Queue[(Int,Int)]()
    queue.enqueue((0,fishMap(0)))

    while (queue.nonEmpty) {
      val previous = queue.dequeue()
      val previousShop = previous._1
      val previousFish = previous._2
      val previousCost = paths(previousShop).getOrElse(previousFish, Int.MaxValue)

      for (current <- roads(previousShop)) {
        val currentShop = current._1
        val currentCost = current._2
        val currentFish = previousFish | fishMap(currentShop)
        val oldCurrentCost = paths(currentShop).getOrElse(currentFish, Int.MaxValue)
        val newCurrentCost = previousCost + roads(previousShop)(currentShop)
        if (newCurrentCost < oldCurrentCost) {
          paths(currentShop)(currentFish) = oldCurrentCost.min(newCurrentCost)
          queue.enqueue((currentShop,currentFish))
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val params = StdIn.readLine().split(" ").map(_.toInt)
    n = params(0)
    m = params(1)
    k = params(2)

    fishMap = new Array(n)

    var i: Int = 0
    while (i < n) {
      fishMap(i) = StdIn.readLine().split(" ").tail.map(_.toInt).foldLeft(0)((a: Int,k: Int) => a + (1 << (k-1)))
      i += 1
    }

    roads = Array.fill(n)(mutable.Map.empty)
    i = 0
    while (i < m) {
      val Array(s,d,t) = StdIn.readLine().split(" ").map(_.toInt)
      roads(s-1) += (d-1) -> t
      roads(d-1) += (s-1) -> t
      i += 1
    }

    paths = Array.fill(n)(mutable.Map.empty)
    paths(0)(fishMap(0)) = 0

    buildAllPath()

    val allFish = fishMap.reduce(_ | _)
    var bestCost = Int.MaxValue
    val costs = paths(n-1).toList
    var big, little: Int = 0
    while (big < costs.length) {
      little = big
      while (little < costs.length) {
        if ((costs(big)._1 | costs(little)._1) == allFish && costs(big)._2.max(costs(little)._2) < bestCost) {
          bestCost = costs(big)._2.max(costs(little)._2)
        }
        little += 1
      }
      big += 1
    }
    println(bestCost)
  }
}
