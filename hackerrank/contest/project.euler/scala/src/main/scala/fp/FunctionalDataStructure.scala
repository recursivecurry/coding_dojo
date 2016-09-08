package fp

/**
  * Created by pi on 2016. 8. 3..
  */
object FunctionalDataStructure {

  def sum(xs: Seq[Int]): Int = xs.foldLeft(0)(_+_)

  def product(xs: Seq[Int]): Int = xs.foldLeft(1)(_*_)

  def flatten[T](as: List[List[T]]): List[T] = {
    def concatList(xss: List[T], yss: List[T]): List[T] = xss match {
      case Nil => yss
      case (x :: xs) => x :: concatList(xs, yss)
    }

    as.foldRight(List.empty[T])(concatList)
  }

  def map[A,B](as: List[A])(f: A => B): List[B] = as.foldRight(List.empty[B])((a: A,b: List[B]) => f(a) :: b)

  def filter[A](as: List[A])(f: A => Boolean): List[A] = as.foldRight(List.empty[A])((a,b) => if (f(a)) a :: b else b)

  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = flatten(map(as)(f))

  def zipWith[A,B,C](ass: List[A], bss: List[B])(f: (A,B) => C): List[C] = (ass,bss) match {
    case (a :: as, b :: bs) => f(a,b) :: zipWith(as, bs)(f)
    case _ => List.empty[C]
  }

  def main(args: Array[String]): Unit = {
    println(sum(List(1, 2, 3, 4, 5)))
    println(product(List(1, 2, 3, 4, 5)))
    println(flatten(List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))))
    println(map(List(1, 2, 3))(_+1))
    println(filter(List(1,2,3,4,5))(_ % 2 == 0))
    println(flatMap(List(1,2,3))(x => List(x + 2)))
    println(zipWith(List(1,2,3),List(4,5,6))(_ + _))
  }
}
