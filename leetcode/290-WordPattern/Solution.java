public class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character,String> patterns = new HashMap<>();
        String[] strs = str.split(" ");
        if (strs.length != pattern.length()) {
            return false;
        }
        boolean match = true;
        for (int i=0; i<pattern.length(); i++) {
            if (patterns.containsKey(pattern.charAt(i))) {
                if (!patterns.get(pattern.charAt(i)).equals(strs[i])) {
                   match = false;
                   break;
                }
            } else {
                if (patterns.containsValue(strs[i])) {
                    match = false;
                    break;
                }
                patterns.put(pattern.charAt(i), strs[i]);
            }
        }
        return match;
    }
}
