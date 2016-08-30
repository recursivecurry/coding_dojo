import scala.io.StdIn
    
object Solution {

    def main(args: Array[String]) {
        val s = StdIn.readLine()
        val cols: Int = math.sqrt(s.length).ceil.toInt
    
        println(s.toArray.grouped(cols).toArray.transpose.map(_.mkString).mkString(" "))
    }
}
