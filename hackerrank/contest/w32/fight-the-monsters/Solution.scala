import scala.collection.mutable

object Solution {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt();
    val hit = sc.nextInt();
    val t = sc.nextInt();
    val q: mutable.PriorityQueue[Int] = mutable.PriorityQueue.empty[Int](Ordering[Int].reverse)

    (0 until n) foreach(_ => {
      q += sc.nextInt()
    })

    val ans: (Int, Int) = q.dequeueAll.foldLeft((t, 0))((acc, h) => {
      if (h / hit <= acc._1 && h % hit == 0)
        (acc._1 - (h / hit), acc._2 + 1)
      else if (h / hit < acc._1)
        (acc._1 - (h / hit) - 1, acc._2 + 1)
       else
        acc
    })
    println(ans._2)
  }
}
