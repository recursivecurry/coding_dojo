public class Solution {
    public int missingNumber(int[] nums) {
        int ans = 0;
        for (int n=0; n<=nums.length; n++) {
            ans ^= n;
        }
        for (int n : nums) {
            ans ^= n;
        }
        return ans;
    }
}
