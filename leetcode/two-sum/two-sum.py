class Solution:
    # @param {integer[]} nums
    # @param {integer} target
    # @return {integer[]}
    def twoSum(self, nums, target):
        idxnums = sorted([(n[1], n[0]) for n in enumerate(nums)])
        for idx, fst in enumerate(idxnums):
            low = idx+1
            high = len(nums) - 1
            while low <= high:
                mid = (low + high) // 2
                rem = target - fst[0]
                #print(low,mid, high,rem)
                if rem < idxnums[mid][0]:
                    high = mid - 1
                elif idxnums[mid][0] < rem:
                    low = mid + 1
                else:
                    return sorted([idxnums[idx][1]+1, idxnums[mid][1]+1])

if __name__ == "__main__":
    s = Solution()
    print(s.twoSum([2, 7, 11, 15], 9))
