public class Solution {
    public int strStr(String haystack, String needle) {
                int haystackLength = haystack.length();
        int needleLength = needle.length();
        for (int i=0; i<=haystackLength-needleLength; i++) {
            boolean flag = true;
            for (int j=0; j<needleLength; j++) {
                if (haystack.charAt(i+j)!=needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
}
