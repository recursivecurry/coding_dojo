public class Solution {
    public int arrangeCoins(int n) {

        long low = 0;
        long high = (long)n+1;

        while (low < high) {
            long mid = (low + high) / 2;
            long full = mid * (mid + 1) / 2;
            if (n < full) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int)(low-1);
    }

    public static void main(String[] args) {
    }
}


