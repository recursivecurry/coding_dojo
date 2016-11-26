public class Solution {
    public List<Integer> majorityElement1(int[] nums) {
        int base = nums.length / 3;
        HashMap<Integer,Integer> countMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            countMap.compute(nums[i], (k,v) -> {
                if (v==null) {
                    return 1;
                } else {
                    if (v+1 > base) {
                        resultList.add(k);
                    }
                    return v + 1;
                }
            });
        }
        return resultList;
    }
    public List<Integer> majorityElement(int[] nums) {
        int[] keys = new int[]{0,0};
        int[] counts = new int[]{0,0};
        for (int i=0; i<nums.length; i++) {
            int current = nums[i];
            if (keys[0] == current) {
                counts[0] += 1;
            } else if (keys[1] == current) {
                counts[1] += 1;
            } else if (counts[0] == 0) {
                keys[0] = current;
                counts[0] = 1;
            } else if (counts[1] == 0) {
                keys[1] = current;
                counts[1] = 1;
            } else {
                counts[0] -= 1;
                counts[1] -= 1;
            }
        }
        List<Integer> result = new ArrayList<>();
        counts[0] = 0;
        counts[1] = 0;
        for (int i=0; i<nums.length; i++) {
            int current = nums[i];
            if (keys[0] == current) {
                counts[0] += 1;
            } else if (keys[1] == current) {
                counts[1] += 1;
            }
        }
        if (counts[0] > nums.length / 3) {
            result.add(keys[0]);
        }
        if (counts[1] > nums.length / 3) {
            result.add(keys[1]);
        }
        return  result;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        log.info(String.valueOf(solution.majorityElement(new int[]{1,2,2,3,2,1,1,3})));
        log.info(String.valueOf(solution.majorityElement(new int[]{2,2,1,3})));
        log.info(String.valueOf(solution.majorityElement(new int[]{2,2})));
        log.info(String.valueOf(solution.majorityElement(new int[]{3,2,3})));
    }
}
