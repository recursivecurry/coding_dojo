import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        int k = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                c = (char)((c + k - 'A') % 26 + 'A');
            } else if (Character.isLowerCase(c)) {
                c = (char)((c + k - 'a') % 26 + 'a');
            }
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
