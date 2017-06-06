import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cs = new int[n];
        for (int i = 0; i < n; i++) {
            cs[i] = sc.nextInt();
        }
        if (n == 1) {
            System.out.println(1);
            return;
        }
        int[] as = new int[n];
        as[0] = 1;
        for (int i = 1; i < n; i++) {
            if (cs[i - 1] < cs[i]) {
                as[i] = as[i - 1] + 1;
            } else {
                as[i] = 1;
            }
        }
        for (int i = n-2; i>=0; i--) {
            if (cs[i] > cs[i+1]) {
                as[i] = Math.max(as[i+1]+1, as[i]);
            }
        }
        long sum = 0;
        for (int a : as) {
            sum += a;
        }
        System.out.println(sum);
    }
}
