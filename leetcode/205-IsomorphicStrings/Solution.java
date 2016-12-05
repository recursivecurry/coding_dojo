public class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> matching = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            if (matching.containsKey(s.charAt(i))) {
                if (!matching.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            } else {
                if (matching.containsValue(t.charAt(i))) {
                    return false;
                }
                matching.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }
}
