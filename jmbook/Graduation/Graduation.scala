import scala.io.StdIn
import scala.collection.immutable.BitSet
import scala.collection.mutable

object Graduation {

  val cache: mutable.Map[(Int, BitSet), Int] = mutable.Map[(Int,BitSet),Int]()
  def main(args: Array[String]): Unit = {
    val c = StdIn.readLine().toInt

    for (_ <- 0 until c) test()
  }

  def test(): Unit = {
    val Array(n, k, m, l) = StdIn.readLine().split(" ").map(_.toInt)

    val subjects: IndexedSeq[BitSet] =
      for (_ <- 0 until n) yield BitSet(StdIn.readLine().split(" ").tail.map(_.toInt): _*)

    val semesters: IndexedSeq[BitSet] =
      for (_ <- 0 until m) yield BitSet(StdIn.readLine().split(" ").tail.map(_.toInt): _*)

    val ans = solve(0, BitSet.empty, k, m, l, subjects, semesters)
    println(if (ans == Int.MaxValue) "INF" else ans)
  }

  def solve(now: Int, taken: BitSet, k: Int, m: Int, l: Int, subjects: IndexedSeq[BitSet], semesters: IndexedSeq[BitSet]): Int = {

    cache.getOrElse((now, taken),
      if (taken.size >= k)
        now - 1
      else if (now >= m)
        Int.MaxValue
      else {
        val availableSubjects: BitSet = BitSet((for (s <- semesters(now) &~ taken if subjects(s).subsetOf(taken)) yield s).toArray: _*)
        (for (subs <- availableSubjects.subsets() if subs.size <= l) yield Int.MaxValue.min(solve(now+1, taken | subs, k, m, l, subjects, semesters))).min
      }
    )
  }
}

/*
Sample #0
1
4 4 4 4
0
1 0
3 0 1 3
0
4 0 1 2 3
4 0 1 2 3
3 0 1 3
4 0 1 2 3

3

Sample #1
1
4 2 2 4
1 1
0
1 3
1 2
3 0 2 3
3 1 2 3

INF
*/

