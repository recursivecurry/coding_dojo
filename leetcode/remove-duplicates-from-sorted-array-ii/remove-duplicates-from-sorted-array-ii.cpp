class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int s = nums.size();
        int count = 0;
        for (int i=0; i<s;) {
            if (1 < i && nums[i] == nums[i-2]) {
                nums.erase(nums.begin()+i);
                s--;
            } else {
                i++;
            }
        }
        return nums.size();
    }
};
