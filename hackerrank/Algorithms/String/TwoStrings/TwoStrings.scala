import scala.io.StdIn

object Solution {

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    (0 until n).foreach(_ => {
      val a = StdIn.readLine()
      val b = StdIn.readLine()
      val aCharSet = a.toCharArray.toSet
      if (b.toCharArray.exists(aCharSet.contains(_)))
          println("YES")
      else
          println("NO")
        
    })
  }
}
