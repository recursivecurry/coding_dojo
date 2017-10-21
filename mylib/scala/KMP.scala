object KMP {
  def buildTable(w: String): Array[Int] = {
    val t: Array[Int] = new Array[Int](w.length+1)
    t(1) = 0
    var k = 0
    (1 until w.length)
      .foreach(q => {
        while (k > 0 && w(k) != w(q)) {
          k = t(k)
        }
        if (w(k) == w(q)) {
          k += 1
        }
        t(q+1) = k
      })
    t
  }

  def matchAll(s: String, w: String): List[Int] = {
    val n = s.length
    val m = w.length
    var q = 0
    val matches = List.newBuilder[Int]
    val t = buildTable(w)
    (0 until n)
      .foreach(i => {
        while(q > 0 && w(q) != s(i)) {
          q = t(q)
        }
        if (w(q) == s(i)) {
          q += 1
        }
        if (q == m) {
          matches += (i-m+1)
          q = t(q)
        }
      })
    matches.result()
  }
}

object Solution {
  def main(args: Array[String]): Unit = {
    val s1 = "abcababcabababc"
    val w1 = "ababc"
    assert(KMP.matchAll(s1, w1) == List(3, 10))
    val s2 = "abababacabababaca"
    val w2 = "ababaca"
    assert(KMP.matchAll(s2, w2) == List(2, 10))
  }
}
