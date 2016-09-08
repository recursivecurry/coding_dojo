package fp

//import scala.collection.{Option => _, Either => _, _}
//import scala.collection.{Stream => _, _}

/**
  * Created by pi on 2016. 8. 10..
  */
sealed trait Stream[+T] {
  def toList: List[T] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList
  }

  def take(n: Int): Stream[T] = this match {
    case Cons(h, t) if n > 0 => Stream.cons(h(), t().take(n-1))
    case _ => Empty
  }

  def drop(n: Int): Stream[T] = this match {
    case Cons(h, t) if n > 0 => t().drop(n-1)
    case _ => Empty
  }

  def takeWhile(p: T => Boolean): Stream[T] = this match {
    case Cons(h, t) if p(h()) => Stream.cons(h(), t().takeWhile(p))
    case _ => Empty
  }
}
case object Empty extends Stream[Nothing]
case class Cons[+T](h: () => T, t: () => Stream[T]) extends Stream[T]

object Stream {
  def cons[A](h: => A, t: => Stream[A]): Stream[A] = {
    lazy val head = h
    lazy val tail = t
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] = {
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
  }

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case None => Empty
    case Some((a, s)) => cons(a, unfold(s)(f))
  }
}

object Main {

  def main(args: Array[String]): Unit = {
    println(Stream.unfold(10)(s => if (s<0) None else Some((s, s-1))).take(20).toList)
    println(Stream.unfold(10)(s => if (s<0) None else Some((s, s-1))).take(5).toList)
  }
}
