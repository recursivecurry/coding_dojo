import scala.io.StdIn

object UkUs {

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()

    val words = (0 until n)
      .flatMap((_) => {
        StdIn.readLine().split("\\s+")
      })
      .filter((w) => "ou?r".r.findFirstIn(w).isDefined)
      .map(_.replaceAll("or", "our"))
      .groupBy(identity)
      .mapValues(_.size)

    val t = StdIn.readInt()

    (0 until t).foreach((_) => {
      val target = StdIn.readLine()
      println(words.getOrElse(target, 0))
    })
  }
}

