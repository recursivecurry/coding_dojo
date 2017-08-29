import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static void reverse(int[] a, int s, int e) {
        while (s < e) {
            a[s] ^= a[e];
            a[e] ^= a[s];
            a[s] ^= a[e];
            s++;
            e--;
        }
    }
    
    static int[] leftRotation(int[] a, int d) {
        reverse(a, 0, d-1);
        reverse(a, d, a.length-1);
        reverse(a, 0, a.length-1);
        return a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] result = leftRotation(a, d);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");


        in.close();
    }
}
