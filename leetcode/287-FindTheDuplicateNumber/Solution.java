public class Solution {
    public int findDuplicate(int[] nums) {
        return binarySearch(nums);
    }

    private int binarySearch(int[] nums) {
        int low = 1;
        int high = nums.length;

        while (low < high) {
            int mid = (low + high) / 2;
            int count = 0;
            for (int n : nums) {
                if (low <= n && n <= mid) count++;
            }
            if (count > (mid - low + 1)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    // not completed
    private int hareTor(int[] nums) {
        int n = nums.length;
        int tor = nums[0];
        int hare = nums[0];

        do {
            tor = move(nums, tor);
            hare = move(nums, move(nums, hare));
        } while (tor != hare);
        tor = nums[0];
        while (tor != hare) {
            tor = move(nums, tor);
            hare = move(nums, hare);
        }
        return tor;
    }

    private int move(int[] nums, int current) {
        if (nums[current-1] == current) {
            current = (current % nums.length) + 1;
            return move(nums, current);
        } else {
            return nums[current-1];
        }
    }
}
