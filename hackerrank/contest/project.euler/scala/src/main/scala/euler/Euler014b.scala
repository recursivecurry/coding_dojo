package euler

object Euler014b {

  import scala.io.StdIn

  var cacheHit = 0L
  var cacheSet = 0L
  val MAX = 5000000
  val termsMap: Array[Long] = Array.ofDim(MAX*2)
  val maxList =
    (1 to MAX)
      .map((n) => (n, terms2(n)))
      .scanLeft((0L,0L))((p,c) => {
        if (p._2 <= c._2)
          (c._1, c._2)
        else
          (p._1, p._2)
      })
    .map(_._1)

  def hasCache(n: Long): Boolean = {
    if (n < termsMap.size && termsMap(n.toInt) != 0) {
      return true
    } else {
      cacheHit += 1
      return false
    }
  }

  def setCache(n: Long, value: Long): Unit = {
    if (n < termsMap.size) {
      cacheSet += 1
      termsMap(n.toInt) = value
    }

  }

  def terms2(n: Long): Long = {
    if (n % 100000 == 0)
      println("N: " + n)

    if (n==1) {
      setCache(1, 1)
      return 1
    }
    var cur = n
    var count = 0
    while (!hasCache(cur)) {
      cur = if (cur % 2 == 0) cur / 2 else cur * 3 + 1
      count += 1
    }
    val ans = termsMap(cur.toInt) + count
    setCache(n, ans)
    return ans
  }

  def main(args: Array[String]): Unit = {
    termsMap(1) = 1
    println(termsMap.last)
    val T = StdIn.readInt()
    (0 until T).foreach((_) => println(maxList(StdIn.readInt())))
  }

}
