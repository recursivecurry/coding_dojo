int removeDuplicates(int* nums, int numsSize) {
    int *ans = (int*)malloc(sizeof(int)*numsSize);
    int dest = 0;
    for (int i=0; i<numsSize; i++) {
        if (i < 2) {
            *(ans+dest++) = nums[i];
        } else {
            if (*(ans+dest-2) != nums[i]) {
                *(ans+dest++) = nums[i];
            }
        }
    }
    memcpy(nums, ans, sizeof(int)*dest);
    return dest;
}
