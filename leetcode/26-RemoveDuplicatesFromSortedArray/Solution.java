public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int prev = 0;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] != nums[prev]) {
                nums[++prev] = nums[i];
            }
        }
        return prev+1;
    }
}
