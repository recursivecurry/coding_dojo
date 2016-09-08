package fp

import scala.{Option => _, Either => _, _}

sealed trait Option[+A] {

  def map[B](f: A => B): Option[B] = this match {
    case Some(x) => Some(f(x))
    case _ => None
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case Some(x) => f(x)
    case _ => None
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }

  def orElse[B >: A](default: => Option[B]): Option[B] = this match {
    case None => default
    case x => x
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(x) => if (f(x)) Some(x) else None
    case _ => None
  }
}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C] = (a, b) match {
    case (Some(a), Some(b)) => Some(f(a,b))
    case _ => None
  }

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case (None :: _) => None
    case (Some(af) :: at) => sequence(at) match {
      case Some(rl) => Some(af :: rl)
      case _ => None
    }
    case Nil => Some(Nil)
  }
}

object OptionMain {

  def main(args: Array[String]): Unit = {
    println(Some(1).map(_ + 2))
    println(Option.sequence(List(None, Some(2))))
    println(Option.sequence(Nil))
  }
}
