package fp

import Stream._

import scala.annotation.tailrec

/**
  * Created by pi on 2016. 8. 11..
  */
trait RNG {
  def nextInt: (Int, RNG);
}

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}

object SimpleRNG {

  def nonNegativeInt(rng: RNG): (Int, RNG) = rng.nextInt match {
    case (x, rng) if x < 0 => (-(x+1), rng)
    case x => x
  }

  def double(rng: RNG): (Double, RNG) = nonNegativeInt(rng) match {
    case (x, rng) => (x.toDouble / Double.MaxValue, rng)
  }

  def intDouble(rng: RNG): ((Int,Double), RNG) = {
    val (i, rng2) = rng.nextInt
    val (d, rng3) = double(rng2)
    ((i,d), rng3)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val (i, rng2) = rng.nextInt
    val (d, rng3) = double(rng2)
    ((d, i), rng3)
  }

  def double3(rng: RNG): ((Double,Double,Double), RNG) = {
    val (d1, rng2) = double(rng)
    val (d2, rng3) = double(rng2)
    val (d3, rng4) = double(rng3)
    ((d1, d2, d3), rng4)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    @tailrec
    def intIter(count: Int, values: List[Int], rng: RNG): (List[Int], RNG) = {
      if (count == 0) {
        (values, rng)
      } else {
        val (i, r) = rng.nextInt
        intIter(count-1,  i +: values, r)
      }
    }
    intIter(count, List(), rng)
  }

  def main(args: Array[String]): Unit = {
    val rng = SimpleRNG(0)
    val (n, nextRNG) = rng.nextInt
    val (n2, nextRNG2) = nextRNG.nextInt
    val (n3, _) = nextRNG2.nextInt
    println(n, n2, n3)
    val (is, r) = ints(10)(SimpleRNG(10))
    println(is)
  }
}
