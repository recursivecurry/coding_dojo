package euler

import org.scalatest._


/**
  * Created by jongsoo.lee on 2016. 4. 17..
  */
class Euler012Spec extends FlatSpec with Matchers {

  "testNumberOfDivisor" should "return the number of divosors" in {
    Euler012.numberOfDivisor(1) should be (1)
    Euler012.numberOfDivisor(2) should be (2)
    Euler012.numberOfDivisor(4) should be (3)
    Euler012.numberOfDivisor(6) should be (4)
    Euler012.numberOfDivisor(12) should be (6)
  }

  "nthTriangleNumber" should "return the nth triangle number" in {
    Euler012.nthTriangleNumber(1) should be (1)
    Euler012.nthTriangleNumber(2) should be (3)
    Euler012.nthTriangleNumber(3) should be (6)
    Euler012.nthTriangleNumber(4) should be (10)
    Euler012.nthTriangleNumber(5) should be (15)
    Euler012.nthTriangleNumber(6) should be (21)
    Euler012.nthTriangleNumber(7) should be (28)
  }
/*
  "numberOfDivisorOfTriangleNum" should "return the number of divisors of nth triangle number" in {
    Euler012.numberOfDivisorOfnthTriangleNum(1) should be (1)
    Euler012.numberOfDivisorOfnthTriangleNum(2) should be (2)
    Euler012.numberOfDivisorOfnthTriangleNum(3) should be (4)
    Euler012.numberOfDivisorOfnthTriangleNum(4) should be (4)
    Euler012.numberOfDivisorOfnthTriangleNum(5) should be (4)
    Euler012.numberOfDivisorOfnthTriangleNum(6) should be (4)
    Euler012.numberOfDivisorOfnthTriangleNum(7) should be (6)
  }
*/
  "solve" should "return correct answer of project euler 12" in {
    Euler012.solve(1) should be (3)
    Euler012.solve(2) should be (6)
    Euler012.solve(3) should be (6)
    Euler012.solve(4) should be (28)

  }
}
