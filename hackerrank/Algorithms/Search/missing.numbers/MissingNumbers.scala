import scala.io.StdIn
import scala.collection.SortedSet
import scala.annotation.tailrec
    
object Solution {

    def findMissing(as: List[Int], bs: List[Int]): SortedSet[Int] = {

    @tailrec
    @unchecked
    def findMissing(as: List[Int], bs: List[Int], missing: SortedSet[Int]): SortedSet[Int] =
      (as, bs) match {
        case (a :: ass, b :: bss) if a == b => findMissing(ass, bss, missing)
        case (a :: ass, b :: bss) => findMissing(as, bss, missing + b)
        case _ => missing ++ bs
      }
    findMissing(as, bs, SortedSet.empty)
  }

  def main(args: Array[String]): Unit = {
    StdIn.readInt()
    val as: List[Int] = StdIn.readLine().split(" ").map(_.toInt).sorted.toList
    StdIn.readInt()
    val bs: List[Int] = StdIn.readLine().split(" ").map(_.toInt).sorted.toList
    print(findMissing(as, bs).toArray.mkString(" "))
  }
}
