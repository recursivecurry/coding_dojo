import scala.io.StdIn
    
object TreasureHunting {

  def main(args: Array[String]): Unit = {
    val Array(x, y) = StdIn.readLine().split(" ").map(_.toDouble)
    val Array(a, b) = StdIn.readLine().split(" ").map(_.toDouble)

    val k = ((a * x) + (b * y)) / (math.pow(a, 2)+math.pow(b, 2))
    val n = ((a * y) - (b * x)) / (math.pow(a, 2)+math.pow(b, 2))
        
    println("%.12f".format(k))
    println("%.12f".format(n))
  }
}
