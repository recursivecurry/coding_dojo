public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        dfs(answer, numbers, candidates, 0, target);
        return answer;
    }

    private void dfs(List<List<Integer>> answer, List<Integer> numbers, int[] candidates, int current, int target ) {
        if (target == 0) {
            answer.add(new ArrayList<>(numbers));
            return;
        }

        if (target < 0) {
            return;
        }

        int previous = -1;
        for (int i=current; i<candidates.length; i++) {
            if (candidates[i] != previous) {
                numbers.add(candidates[i]);
                dfs(answer, numbers, candidates, i+1, target - candidates[i]);
                numbers.remove(numbers.size()-1);
                previous = candidates[i];

            }
        }
    }
}
