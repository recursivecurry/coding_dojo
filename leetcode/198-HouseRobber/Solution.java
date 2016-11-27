public class Solution {
    public int rob(int[] nums) {
        int dp[] = new int[nums.length+1];
        if (nums.length==0)
            return 0;
        if (nums.length==1)
            return nums[0];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i=1; i<nums.length; i++) {
            int include = dp[i-1] + nums[i];
            int exclude = dp[i];
            dp[i+1] = include > exclude ? include : exclude;
        }
        return dp[nums.length];
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        log.info("{}", solution.rob(new int[]{1, 3, 5, 2, 7, 9, 5}));
        log.info("{}", solution.rob(new int[]{2,2,2}));
        log.info("{}", solution.rob(new int[]{}));
        log.info("{}", solution.rob(new int[]{1}));
        log.info("{}", solution.rob(new int[]{1,2}));
        log.info("{}", solution.rob(new int[]{1,2,3}));
    }
}
