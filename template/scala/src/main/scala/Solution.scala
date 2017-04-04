/**
  * Created by pi on 2017. 4. 5..
  */
object Solution {
  @inline
  def numberOfWays(X:Int,N:Int,C:Int):Int = {
    if (X == 0)
      1
    else
      Stream.from(1)
        .takeWhile(n => math.pow(n, N) <= X)
        .map(n => numberOfWays(X - math.pow(n, N).toInt, N, n + 1))
        .sum
  }

  def main(args: Array[String]) {
    println(numberOfWays(readInt(),readInt(), 1))
  }
}
