public class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = nums.length / 2;
        int medianValue = nums[median];
        return Arrays.stream(nums)
                .map(n -> Math.abs(n-medianValue))
                .sum();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}
