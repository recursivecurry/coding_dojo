public class Solution {
    public boolean isPalindrome(int x) {
        return String.valueOf(x).equals(new StringBuilder().append(x).reverse().toString());
    }
}
