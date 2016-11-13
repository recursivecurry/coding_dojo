package algorithm

import scala.collection.immutable.VectorBuilder

class Kmp(val s: String) {
  val pm: Array[Int] = genPartialMatch(s)

  private def genPartialMatch(s: String): Array[Int] = {
    val sl = s.length
    val p = Array.fill(s.length)(0)
    (1 until sl)
      .foreach {
        begin =>
          (0 until sl - begin)
            .takeWhile {
              i =>
                s(begin + i) == s(i)
            }
            .foreach {
              i =>
                p(begin + i) = p(begin + i).max(i + 1)
            }
      }
    p
  }

  def search(t: String): Seq[Int] = {
    val tl = t.length
    val sl = s.length
    val builder = new VectorBuilder[Int]
    var begin = 0
    var matched = 0
    while (begin <= tl - sl) {
      if (matched < sl && t(begin+matched) == s(matched)) {
        matched += 1
        if (matched == sl) {
          builder += begin
        }
      } else {
        if (matched == 0) {
          begin += 1
        } else {
          begin += (matched - pm(matched-1))
          matched = pm(matched-1)
        }
      }
    }
    builder.result
  }
}

object Kmp {

  def main(args: Array[String]): Unit = {
    val kmp = new Kmp("aba")
    val kmp2 = new Kmp("ababac")
    val kmp3 = new Kmp("aabaabac")
    println(kmp.pm)
    val ans1 = kmp.search("ababac")
    val ans2 = kmp.search("abbabaabac")
    println(ans1, ans2)
  }

}

