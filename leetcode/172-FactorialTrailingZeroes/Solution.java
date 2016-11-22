public class Solution {
    public int trailingZeroes(int n) {
        int zeros = 0;
        while (n > 0) {
            n = n / 5;
            zeros += n;
        }
        return zeros;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trailingZeroes(0));
        System.out.println(solution.trailingZeroes(1));
        System.out.println(solution.trailingZeroes(2));
        System.out.println(solution.trailingZeroes(5));
        System.out.println(solution.trailingZeroes(7));
        System.out.println(solution.trailingZeroes(10));
        System.out.println(solution.trailingZeroes(25));
        System.out.println(solution.trailingZeroes(26));
        System.out.println(solution.trailingZeroes(50));
    }
}


