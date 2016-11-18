// answer: http://articles.leetcode.com/longest-palindromic-substring-part-i/
public class Solution {
    public String longestPalindrome(String s) {
        boolean dp[][] = new boolean[s.length()][s.length()];
        int iMax = 0, jMax = 0;
        for (int i=0; i<s.length(); i++) {
            for (int j=i; j<s.length(); j++) {
                if (i==j) {
                    dp[i][j] = true;
                } else if (j - i == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    iMax = i;
                    jMax = j;
                }
            }
        }
        for (int l=3; l<=s.length(); l++) {
            for (int i=0; i<=s.length()-l; i++) {
                int j = i+l-1;
                if (s.charAt(i)==s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    iMax = i;
                    jMax = j;
                }
            }
        }
        return s.substring(iMax, jMax+1);
    }
}
