public class Solution {
    private void find(int open, int close, int n, StringBuilder sb, List<String> result) {
        if (open==n && close==n) {
            result.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append("(");
            find(open+1, close, n, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
        if (open-close > 0) {
            sb.append(")");
            find(open, close+1, n, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        find(0, 0, n, new StringBuilder(), result);
        return result;
    }
}
