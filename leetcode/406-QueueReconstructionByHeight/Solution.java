public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][2];
        int last = 0;

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return (o1[1]-o2[1]);
                } else {
                    return -(o1[0] - o2[0]);
                }
            }
        });

        for (int[] p : people) {
            insert(result, p);
        }

        return people;
    }

    private static boolean isEmpty(int[] p) {
        return p[0]==0 && p[1]==0;
    }

    private static void insert(int[][] result, int[] p) {
        for (int i=0; i<result.length; i++) {
            if (isEmpty(result[i])) {
                result[i] = p;
            }
            if (i==p[i]) {
                
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] result = solution.reconstructQueue(new int[][]{new int[]{7,0}, new int[]{4,4}, new int[]{7,1}, new int[]{5,0}, new int[]{6,1}, new int[]{5,2}});
        for (int[] i : result) {
            log.info("{},{}", i[0], i[1]);
        }
    }
}
