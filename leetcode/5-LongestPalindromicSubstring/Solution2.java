public class Solution {
    public String longestPalindrome(String s) {
        StringBuffer bf = new StringBuffer("#");
        for (char c : s.toCharArray()) {
            bf.append(c).append('#');
        }
        String t = bf.toString();
        int n = t.length();
        int a[] = new int[n];

        int p = -1;
        int r = -1;
        int max = 0;
        int maxP = 0;
        for (int i=1; i<n; i++) {
            if (i <= r) {
                a[i] = Math.min(r-i, a[p-(i-p)]);
            } else {
                a[i] = 0;
            }
            while ((i-a[i]-1>0) && (i+a[i]+1<n) && t.charAt(i-a[i]-1) == t.charAt(i+a[i]+1)) {
                a[i] += 1;
            }
            if (i+a[i]>r) {
                r = i + a[i];
                p = i;
                if (a[i] > max) {
                    max = a[i];
                    maxP = p;
                }
            }
        }
        return t.substring(maxP-max, maxP+max+1).replace("#", "");
    }
}


