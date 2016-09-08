package fp

/**
  * Created by pi on 2016. 5. 8..
  */
object MyList {

  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean =  sup match {
    case Nil => false
    case _ => if (sup.take(sub.length) == sub) true else hasSubsequence(sup.drop(1), sub)
  }

  def main(args: Array[String]): Unit =  {
    println(hasSubsequence(List(0,1,2,3,4,5), List(2,3,4)))
    println(hasSubsequence(List(0,1,3,4,5), List(2,3,4)))
  }
}
