import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn


object GravityTree {

  case class Node(var height: Int, parents: ArrayBuffer[Int], children: ArrayBuffer[Int], var squared: Long, var sum: Long, var all: Int)

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val nodes: ArrayBuffer[Node] = ArrayBuffer.fill(n+1)(Node(0, ArrayBuffer.fill(20)(0), new ArrayBuffer[Int](), 0, 0, 0))
    nodes(0) = null
    nodes(1).height = 0
    StdIn.readLine().split(" ")
      .map(_.toInt)
      .zipWithIndex
      .foreach(
        (pi) => {
          val parent = pi._1
          val self = pi._2 + 2
          nodes(self).parents(0) = pi._1
          nodes(parent).children.append(self)
        }
      )

    val queue: mutable.Queue[Int] = mutable.Queue(nodes(1).children: _*)
    val upQueue: mutable.Queue[Int] = mutable.Queue()
    while (queue.nonEmpty) {
      val current = queue.dequeue()
      val currentNode = nodes(current)
      currentNode.height = nodes(currentNode.parents(0)).height + 1
      if (currentNode.children.isEmpty) {
        upQueue.enqueue(current)
      } else {
        queue.enqueue(currentNode.children: _*)
      }
    }

    while (upQueue.nonEmpty) {
      val current = upQueue.dequeue()
      val currentNode = nodes(current)
      if (currentNode.children.isEmpty) {
        currentNode.squared = 1
        currentNode.sum = 1
        currentNode.all = 1
      } else {
        val childSum = currentNode.children.foldLeft((0L,0L,0))((a,c) => (a._1 + nodes(c).squared, a._2 + nodes(c).sum, a._3 + nodes(c).all))
        currentNode.all = childSum._3 + 1
        currentNode.squared = childSum._1 + 2 * childSum._2 + currentNode.all
        currentNode.sum = childSum._2 + currentNode.all
      }
      if (currentNode.parents(0) != 1) {
        upQueue.enqueue(currentNode.parents(0))
      }
    }

    val q = StdIn.readInt()
    (0 until q).foreach(_ => {
      val Array(sink, sources) = StdIn.readLine().split(" ").map(_.toInt)
      val lca = if (nodes(sink).height > nodes(sources).height) findLCA(nodes, sources, sink) else findLCA(nodes, sink, sources)

      val lcaNode = nodes(lca)
      val uNode = nodes(sink)
      val vNode = nodes(sources)

      var ans = 0L

      if (lca == sink) {
        val c = vNode.height - lcaNode.height

        ans = gravity(nodes, c, sources)
      } else if (lca == sources) {
        ans += uNode.children.map(gravity(nodes, 1, _)).sum
        var current = sink
        var previous = sink
        var currentHeight = uNode.height - 1
        while (currentHeight >= vNode.height) {
          current = nodes(current).parents(0)
          val currentNode = nodes(current)
          val c = uNode.height - currentNode.height
          ans += (c * c)
          ans += currentNode.children.filter(_!=previous).map(gravity(nodes, c+1, _)).sum
          previous = current
          currentHeight -= 1
        }
      } else {
        val c = uNode.height + vNode.height - lcaNode.height *2
        ans = gravity(nodes, c, sources)
      }
      println(ans)
    })
  }

  def gravity(nodes: ArrayBuffer[Node], c: Int, r: Int): Long = {
    val childSum = nodes(r).children.foldLeft((0L,0L,0))((a,c) => (a._1 + nodes(c).squared, a._2 + nodes(c).sum, a._3 + nodes(c).all))
    val g = childSum._1 + 2 * c * childSum._2 + (c * c) * (childSum._3 + 1)
    g
  }

  @tailrec
  def findLCA(nodes: ArrayBuffer[Node], low: Int, high: Int): Int = {
    val lowNode = nodes(low)
    val highNode = nodes(high)

    if (low == high)
      low
    else if (lowNode.height == highNode.height) {
      var next = 0
      while (findParent(nodes, low, next) != findParent(nodes, high, next)) {
        next += 1
      }
      if (next == 0)
        findLCA(nodes, findParent(nodes, low, next), findParent(nodes, high, next))
      else
        findLCA(nodes, findParent(nodes, low, next-1), findParent(nodes, high, next-1))
    } else {
      val diff = Integer.highestOneBit(nodes(high).height - nodes(low).height)
      findLCA(nodes, low, findParent(nodes, high, 31 - Integer.numberOfLeadingZeros(diff)))
    }
  }

  def findParent(nodes: ArrayBuffer[Node], i: Int, next: Int): Int = {
    val me = nodes(i)

    if (31 - Integer.numberOfLeadingZeros(me.height) < next)
      1
    else {
      if (me.parents(next) == 0) {
        val parent = findParent(nodes, findParent(nodes, i, next-1), next-1)
        me.parents(next) = parent
      }
      me.parents(next)
    }
  }
}

