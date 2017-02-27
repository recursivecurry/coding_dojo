public class Solution {
    private static final boolean isDfs = false;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (isDfs) {
            boolean[] visited = new boolean[numCourses];
            boolean[] loop = new boolean[numCourses];
            List<List<Integer>> courses = new ArrayList<>();
            for (int i = 0; i< numCourses; i++) {
                courses.add(new ArrayList<>());
            }
            Deque<Integer> order = new ArrayDeque<>();
            for (int i = 0; i < prerequisites.length; i++) {
                courses.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!dfs(i, courses, visited, loop, order)) return new int[]{};
            }

            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = order.pop();
            }
            return result;
        } else {
            int[] inCount = new int[numCourses];
            List<List<Integer>> courses = new ArrayList<>();
            for (int i = 0; i< numCourses; i++) {
                courses.add(new ArrayList<>());
            }
            for (int i = 0; i < prerequisites.length; i++) {
                courses.get(prerequisites[i][1]).add(prerequisites[i][0]);
                inCount[prerequisites[i][0]] += 1;
            }
            return bfs(numCourses, inCount, courses);
        }
    }

    private int[] bfs(int numCourses, int[] inCount, List<List<Integer>> courses) {
        int pos = 0;
        int[] order = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inCount[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[pos++] = current;
            for (int next : courses.get(current)) {
                inCount[next] -= 1;
                if (inCount[next] == 0) queue.add(next);
            }
        }
        for (int count : inCount) {
            if (count > 0) return new int[0];
        }
        return order;
    }

    private boolean dfs(int current, List<List<Integer>> courses, boolean[] visited, boolean[] loop, Deque<Integer> order) {
        if (visited[current]) return true;
        if (loop[current]) return false;
        loop[current] = true;
        for (Integer next : courses.get(current)) {
            if (!dfs(next, courses, visited, loop, order)) return false;
        }
        visited[current] = true;
        order.push(current);
        return true;
    }
}
