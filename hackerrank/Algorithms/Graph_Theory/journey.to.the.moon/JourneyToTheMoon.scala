import scala.collection.{mutable, _}
import scala.io.StdIn
  
object Solution {
  def main(args: Array[String]): Unit = {
    val Array(n, i) = StdIn.readLine().split(" ").map(_.toInt)

    val countryMap: mutable.Map[Int, mutable.Set[Int]] = mutable.Map.empty.withDefaultValue(mutable.Set.empty)
    val idArray: Array[Int] = Array.fill(n)(0)
    var countryId = 0

    (0 until i).foreach((_) => {
      val Array(p1, p2) = StdIn.readLine().split(" ").map(_.toInt)

      countryMap.put(p1, countryMap(p1) + p2)
      countryMap.put(p2, countryMap(p2) + p1)
    })

    for (v <- 0 until n if idArray(v) == 0) {
      countryId += 1
      val next: mutable.Queue[Int] = mutable.Queue(v)

      while (!next.isEmpty) {
        val current: Int = next.dequeue()
        idArray(current) = countryId
        for (np <- countryMap(current) if idArray(np) == 0) {
          next.enqueue(np)
        }
      }
    }
    var result: Long = 0
    var sum: Long = 0
    val countMap = idArray.groupBy(identity)
    for (cs <- countMap.values) {
      val c: Long = cs.size
      result += (sum * c)
      sum += c
    }
    println(result)
  }
}
