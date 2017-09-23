import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    // s[]: 1, 2, 1, 3, 2
    // d: 3, m: 2
    static int solve(int n, int[] s, int d, int m){
        int i;
        int sum = 0;
        int count = 0;
        for (i = 0; i < m-1; i++) {
            sum += s[i];
        }
        sum += s[i++];
        if (sum == d) {
            count++;
        }
        for (; i<n; i++) {
            sum += s[i];
            sum -= s[i-m];
            if (sum == d) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] s = new int[n];
        for(int s_i=0; s_i < n; s_i++){
            s[s_i] = in.nextInt();
        }
        int d = in.nextInt();
        int m = in.nextInt();
        int result = solve(n, s, d, m);
        System.out.println(result);
    }
}
