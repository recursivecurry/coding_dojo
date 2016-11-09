public class Solution {
    public int lengthOfLongestSubstring(String s) {
                Set<Character> currentCharacters = new HashSet<Character>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < s.length(); right++) {
            Character rightCharacter = s.charAt(right);
            if (currentCharacters.contains(rightCharacter)) {
                while (currentCharacters.contains(rightCharacter)) {
                    currentCharacters.remove(s.charAt(left++));
                }
            } else {
                if (max < (right - left + 1)) {
                    max = right - left + 1;
                }
            }
            currentCharacters.add(rightCharacter);
        }

        return max;
    }
}
