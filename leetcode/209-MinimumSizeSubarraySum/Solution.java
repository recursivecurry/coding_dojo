public class Solution {

    public int minSubArrayLen(int s, int[] nums) {
        int head = 0;
        int tail = 0;
        int sum = 0;
        int min = nums.length+1;

        while (head <= nums.length) {
            if (sum < s) {
                if (head == nums.length) {
                    break;
                }
                sum += nums[head];
                head += 1;
            } else {
                if (min > head - tail) {
                    min = head - tail;
                    if (min == 1) {
                        break;
                    }
                }
                sum -= nums[tail];
                tail += 1;
            }
        }
        return min == nums.length+1 ? 0 : min;
    }
}
