import kotlin.math.abs

class Solution {
    inline fun Int.closeThan(other: Int): Boolean {
        val a = abs(this)
        val b = abs(other)
        return if (a == b) {
            this > other
        } else {
            a < b
        }
    }
    fun findClosestNumber(nums: IntArray): Int {
        var closest = 100001
        for (n in nums) {
            if (n.closeThan(closest)) {
                closest = n
            }
        }
        return closest
    }
}
