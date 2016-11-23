public class Solution {
    public boolean repeatedSubstringPattern(String str) {
        int len = str.length();

        if (len <= 1)
            return false;

        for (int i = 1; i < (len / 2) + 1; i++) {
            if (len % i != 0) {
                continue;
            }
            boolean flag = true;
            for (int j = 0; j < (len - i); j++) {
                if (str.charAt(i + j) != str.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        log.info("{}", solution.repeatedSubstringPattern(""));
        log.info("{}", solution.repeatedSubstringPattern("a"));
        log.info("{}", solution.repeatedSubstringPattern("aa"));
        log.info("{}", solution.repeatedSubstringPattern("aba"));
        log.info("{}", solution.repeatedSubstringPattern("abab"));
        log.info("{}", solution.repeatedSubstringPattern("abcabcabcabc"));
    }
}


