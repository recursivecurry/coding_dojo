package fp

/**
  * Created by pi on 2016. 8. 7..
  */
sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Right(a) => Right(f(a))
    case Left(e) => Left(e)
  }
  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => f(a)
    case Left(e) => Left(e)
  }
  def orElse[EE >: E, B >: A](default: => Either[EE, B]): Either[EE, B] = this match {
    case Left(_) => default
    case _ => this
  }
  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = this match {
    case Left(e) => Left(e)
    case Right(aa) => b match {
      case Left(e2) => Left(e2)
      case Right(bb) => Right(f(aa, bb))
    }
  }
}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]
