import scala.io.StdIn
    
object GearsOfWar {

    def main(args: Array[String]) {
        val t = StdIn.readInt()
        for (_ <- 0 until t) {
            val n = StdIn.readInt()
            println(if (n%2==0) "Yes" else "No")
        }
    }
}
