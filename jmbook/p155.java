import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static int first(boolean[] children) {
        int i;
        for (i = 0; i < children.length; i++) {
            if (children[i])
                break;
        }
        return i;
    }

    public static int picnic(boolean[][] friends, boolean[] children) {
        int count = 0;
        int c = first(children);

        if (c == children.length)
            return 1;

        for (int i = 0; i < children.length; i++) {
            if (friends[c][i] && children[i]) {
                children[c] = children[i] = false;
                count += picnic(friends, children);
                children[c] = children[i] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] children = new boolean[n];
        Arrays.fill(children, true);
        boolean[][] friends = new boolean[n][n];

        for (int i=0; i < m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            friends[first][second] = true;
            friends[second][first] = true;
        }
        int ans = picnic(friends, children);
        System.out.println(ans);
    }
}
