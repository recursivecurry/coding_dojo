public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][2];

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1]-o2[1];
            } else {
                return -(o1[0] - o2[0]);
            }
        });

        for (int[] p : people) {
            insert(result, p);
        }

        return result;
    }

    private static void insert(int[][] result, int[] p) {
        for (int i=result.length-1; i>p[1]; i--) {
            result[i] = result[i-1];
        }
        result[p[1]] = p;
    }
}
