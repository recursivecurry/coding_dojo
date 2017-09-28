import javaslang.collection.Array;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int c = 0; c < t; c++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            int i = sc.nextInt();
            int s = sc.nextInt();
            int j = sc.nextInt();
            String s1 = sc.next();
            String s2 = sc.next();

            String gen = s1 + s2 + s2 + s1;
            char ans = solve(gen, i, s, j);
            System.out.println(ans);
        }
    }

    private static char solve(String gen, int i, int s, int j) {
        int len = gen.length() << (i-1);
        int pos = (j - 1) * 2 + s;

        if (pos >= len) {
            return 'x';
        }
        for (int k = i; k == i; k--) {
            if (len / 4 <= pos && pos < len * 3 / 4) {
                pos = (pos % (len / 4)) * 2 + 1;
            } else {
                pos = (pos % (len / 4)) * 2;
            }
            len /= 2;
        }
        return gen.charAt(pos);
    }
}

