class Solution {
public:
    int totalHammingDistance(vector<int>& nums) {
        size_t size = nums.size();
        int distance = 0;
        int zeroCount = size;
        while(zeroCount != 0) {
            zeroCount = size;
            int zero = 0;
            int one = 0;
            for (int i=0; i<size; i++) {
                if (nums[i]==0) zeroCount--;
                if (nums[i]%2==1)
                    one++;
                else
                    zero++;
                nums[i] = nums[i] >> 1;
            }
            distance += zero * one;
        }
        return distance;
    }
};
