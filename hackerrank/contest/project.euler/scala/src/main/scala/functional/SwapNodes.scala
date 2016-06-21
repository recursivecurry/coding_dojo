package functional

import scala.io.StdIn
import scala.collection.immutable._

/**
  * Created by jongsoo.lee on 2016. 6. 21..
  */
object SwapNodes {

  trait Tree
  case class Node(id: Int, left: Tree, right: Tree) extends Tree
  case class None() extends Tree

  def build(nodes: IndexedSeq[(Int,Int)], root: Int): Tree = {
    if (root == -1) {
      None()
    } else {
      Node(root, build(nodes, nodes(root-1)._1), build(nodes, nodes(root-1)._2))
    }
  }

  def swapK(tree: Tree, level: Int, k: Int): Tree = tree match {
    case Node(i, l, r) if level % k == 0 => Node(i, swapK(r, level+1, k), swapK(l, level+1, k))
    case Node(i, l, r) => Node(i, swapK(l, level+1, k), swapK(r, level+1, k))
    case v => v
  }

  def traverse(tree: Tree): Vector[Int] = tree match {
    case None() => Vector()
    case Node(v, l, r) => traverse(l) ++ Vector(v) ++ traverse(r)
  }

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val initialNodes = for {
      _ <- 0 until n
      Array(left, right) = StdIn.readLine().split(' ').map(_.toInt)
    } yield (left, right)
    val initialTree = build(initialNodes, 1)


    val t = StdIn.readInt()
    val ks = for {
      _ <- 0 until t
      k = StdIn.readInt()
    } yield k
    val steps = ks.scanLeft(initialTree)(
      (tree, k) => {
        swapK(tree, 1, k)
      })
    steps.map(traverse).tail.foreach(
      (list) => {
        println(list.mkString(" "))
      })
  }
}
