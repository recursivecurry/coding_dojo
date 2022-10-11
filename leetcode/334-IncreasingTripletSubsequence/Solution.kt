package main

class Solution {
    fun increasingTriplet(nums: IntArray): Boolean {
        var first = Int.MAX_VALUE
        var second = Int.MAX_VALUE
        for (num in nums)
            if (num <= first) first = num
            else if (num <= second)
                second = num
            else
                return true
        return false
    }
//    fun increasingTriplet(nums: IntArray): Boolean {
//        val two = intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE)
//        var one = Int.MAX_VALUE
//        for (n in nums) {
//            when {
//                two[1] < n -> { return true}
//                two[0] < n -> { two[1] = n }
//                one < n  -> { two[0] = one; two[1] = n}
//                else -> { one = n}
//            }
//        }
//        return false
//    }
}

fun main(): Unit {
    val s = Solution()
    println(s.increasingTriplet(intArrayOf(4, 5, 1, 2, 3)))
    println(s.increasingTriplet(intArrayOf(1, 2, 3, 4, 5)))
    println(s.increasingTriplet(intArrayOf(5, 4, 3, 2, 1)))
    println(s.increasingTriplet(intArrayOf(2, 1, 5, 0, 4, 6)))
}
