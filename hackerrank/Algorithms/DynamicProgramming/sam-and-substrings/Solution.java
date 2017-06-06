import java.io.*;
import java.util.*;

public class Solution {

        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] cs = sc.next().toCharArray();
        int[] ns = new int[cs.length];
        for (int i=0; i<cs.length; i++) {
            ns[i] = cs[i] - '0';
        }

        long suffixSum = 0;
        long total = 0;
        for (int i=0; i < ns.length; i++) {
            suffixSum = (suffixSum * 10 + (ns[i] * (i+1))) % 1000000007;
            total += suffixSum;
            total %= 1000000007;
        }
        System.out.println(total);
    }
}
