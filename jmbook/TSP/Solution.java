import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Solution {
    static final int CITY = 4;
    static int n = CITY;
    static int[][] map =
            {
                    {0, 1, 3, 4},
                    {1, 0, 2, 3},
                    {3, 2, 0, 4},
                    {4, 3, 4, 0}
            };

    static int tsp(int current, int passed, boolean[] visit, int distance) {
        if (passed == n) {
            return distance;
        }

        int shortest = Integer.MAX_VALUE;
        for (int next = 0; next < n; next++) {
            if (visit[next]) continue;
            visit[next] = true;
            shortest = Math.min(shortest, tsp(next, passed+1, visit, distance + map[current][next]));
            visit[next] = false;
        }
        return shortest;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(tsp(0, 0, new boolean[n], 0));
    }
}
