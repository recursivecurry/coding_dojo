//class Solution {
//    fun canJump(nums: IntArray): Boolean {
//        val flags = BooleanArray(nums.size)
//        flags[0] = true
//        for ((i, n) in nums.withIndex()) {
//            if (n > 0 && flags[i]) {
//                for (j in i+1 .. i + n) {
//                    if ( j == nums.size) {
//                        break
//                    }
//                    flags[j] = true
//                }
//            }
//        }
//        return flags.last()
//    }
//}

class Solution {
    fun canJump(nums: IntArray): Boolean {
        var t = nums.lastIndex
        var current = t - 1
        while (current >= 0) {
            if (nums[current] >= t - current) {
                t--
                current = t - 1
            } else if (current != 0 ) {
                current--
            } else
                return false

        }
        return true
    }
}
fun main(): Unit {
    val s = Solution()
    println(s.canJump(intArrayOf(2, 3, 1, 1, 4)))
    println(s.canJump(intArrayOf(3, 2, 1, 0, 4)))
}
