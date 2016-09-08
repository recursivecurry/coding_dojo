package fp

/**
  * Created by pi on 2016. 5. 8..
  */
sealed trait MyTree[+A]
case class Leaf[A](value: A) extends MyTree[A]
case class Branch[A](left: MyTree[A], right: MyTree[A]) extends MyTree[A]

object MyTree {
  def size[A](tree: MyTree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(left, right) => 1 + size(left) + size(right)
  }

  def maximum(tree: MyTree[Int]): Int = tree match {
    case Leaf(v) => v
    case Branch(left, right) => maximum(left) max maximum(right)
  }

  def map[A,B](tree: MyTree[A])(f: A => B): MyTree[B] = tree match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l,r) => Branch(map(l)(f), map(r)(f))
  }

  def main(args: Array[String]): Unit = {
    val sampleTree = Branch(Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Leaf(4))
    println(size(sampleTree))
    println(maximum(sampleTree))
  }
}
