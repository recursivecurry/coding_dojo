public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteIter(0, result, nums);
        return result;
    }

    private void permuteIter(int start, List<List<Integer>> result, int[] nums) {
        if (start == nums.length-1) {
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int target=start; target < nums.length; target++) {
            swap(nums, start, target);
            permuteIter(start+1, result, nums);
            swap(nums, start, target);
        }
    }

    private void swap(int[] nums, int from, int to) {
        int temp = nums[to];
        nums[to] = nums[from];
        nums[from] = temp;
    }
}
