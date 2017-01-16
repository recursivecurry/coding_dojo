public class Solution {
    public int numberOfArithmeticSlices(int[] A) {

        if (A.length < 3) {
            return 0;
        }
        int total = 0;
        int gap = A[1] - A[0];
        int begin = 0;

        for (int i=1; i<A.length; i++) {
            if (A[i] - A[i-1] != gap) {
                total += count(i - begin);
                gap = A[i] - A[i-1];
                begin = i-1;
            }
        }
        total += count(A.length - begin);
        return total;
    }

    private int count(int length) {
        if (length < 3) {
            return 0;
        } else if (length == 3) {
            return 1;
        } else {
            length -= 2;
            if (length % 2 == 0) {
                return (1+length)*(length/2);
            } else {
                return (1+length)*(length/2) + (1+length)/2;
            }
        }
    }
}
