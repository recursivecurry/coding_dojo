public class Solution {
    private String range(int begin, int end) {
        if (begin == end) {
            return String.valueOf(begin);
        } else {
            StringBuilder sb = new StringBuilder().append(begin).append("->").append(end);
            return sb.toString();
        }
    }

    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        int begin = nums[0];
        int end = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == end +1) {
                end = nums[i];
            } else {
                result.add(range(begin, end));
                begin = nums[i];
                end = nums[i];
            }
        }
        result.add(range(begin, end));
        return result;
    }
}
