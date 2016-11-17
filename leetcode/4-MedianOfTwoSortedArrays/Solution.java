public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (0 == total % 2) {
            return (find(total/2, nums1, nums2, 0, 0) + find(total/2+1, nums1, nums2, 0, 0)) / 2.0;
        } else {
            return find(total/2+1, nums1, nums2, 0, 0);
        }
    }
    public int find(int k, int[] nums1, int[] nums2, int s1, int s2) {
        if (s1 >= nums1.length) {
            return nums2[s2+k-1];
        }
        if (s2 >= nums2.length) {
            return nums1[s1+k-1];
        }
        if (k==1) {
            return Math.min(nums1[s1], nums2[s2]);
        }
        int m1 = s1 + k/2 - 1;
        int m2 = s2 + k/2 - 1;

        int mid1 = m1 < nums1.length ? nums1[m1] : Integer.MAX_VALUE;
        int mid2 = m2 < nums2.length ? nums2[m2] : Integer.MAX_VALUE;

        if (mid1 < mid2) {
            return find(k - k/2, nums1, nums2, m1+1, s2);
        } else {
            return find(k - k/2, nums1, nums2, s1, m2+1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        double d = s.findMedianSortedArrays(new int[]{1, 3, 4, 6, 7, 8}, new int[]{2, 5, 9, 10});
        System.out.println(d);
    }


}


// 1 4 7 9 10
// 2 5 8
