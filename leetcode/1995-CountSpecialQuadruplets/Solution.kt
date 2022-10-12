class Solution {
    fun countQuadruplets(nums: IntArray): Int {
        var count = 0
        for ((i, a) in nums.withIndex()) {
            for ((j, b) in nums.drop(i + 1).withIndex()) {
                for ((k, c) in nums.drop(i + j + 2).withIndex()) {
                    for ((l, d) in nums.drop(i + j + k + 3).withIndex()) {
                        if (a + b + c == d) {
                            count++
                        }
                    }
                }
            }
        }
        return count
    }
}
