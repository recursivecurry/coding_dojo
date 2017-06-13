import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static final long MOD = 1000000007L;

    private static List<List<Integer>> kingdom;
    private static boolean[][][] done;
    private static long[][][] cache;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        kingdom = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            kingdom.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            List<Integer> edges = kingdom.get(u);
            edges.add(v);
            edges = kingdom.get(v);
            edges.add(u);
        }

        done = new boolean[n + 1][2][2];
        cache = new long[n + 1][2][2];
        
        System.out.println((solve(1, 0, 0, 0, 0) * 2) % MOD);
    }

    private static long solve(int current, int parent, int order, int color, int ally) {
        long res = 0;

        if (order==kingdom.get(current).size()) {
            return ally;
        }

        int child = kingdom.get(current).get(order);

        if (child == parent) {
            return solve(current, parent, order + 1, color, ally);
        }

        if (done[child][color][ally] == true) {
            return cache[child][color][ally];
        }
        done[current][color][ally] = true;

        long same = solve(child, current, 0, color, 1) * solve(current, parent, order+1, color, 1);
        long diff = solve(child, current, 0, 1 - color, 0) * solve(current, parent, order+1, color, ally);

        cache[child][color][ally] = ((same % MOD) + diff) % MOD;
        return cache[child][color][ally];
    }
}
