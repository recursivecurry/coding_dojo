import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        
        int max = 0;
        
        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
                if (c1 != c2) {
                    int count = 0;
                    boolean flag = true;
                    for (char sc : s.toCharArray()) {
                        if (sc == c1) {
                            if (flag) {
                                count++;
                                flag = false;
                            } else {
                                count = 0;
                                break;
                            }
                        } else if (sc == c2) {
                            if (!flag) {
                                count++;
                                flag = true;
                            } else {
                                count = 0;
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    if (count > 1 && count > max) {
                        max = count;
                    }
                }
            }
        }
        System.out.println(max);
    }
}
