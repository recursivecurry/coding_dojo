public class Solution {
    public int myAtoi(String str) {
        String trimmed = str.trim();
        boolean sign = true;
        long sum = 0;
        for (int i=0; i<trimmed.length(); i++) {
            char current = trimmed.charAt(i);
            if (i==0) {
                if (current == '+') {
                    sign = true;
                } else if (current == '-') {
                    sign = false;
                } else if (Character.isDigit(current)){
                    sum += sign ? (trimmed.charAt(i)-'0') : -(trimmed.charAt(i)-'0');
                } else {
                    return 0;
                }
            } else if (Character.isDigit(current)) {
                sum *= 10;
                sum += sign ? (trimmed.charAt(i)-'0') : -(trimmed.charAt(i)-'0');
                if (sum > 2147483647L) {
                    return 2147483647;
                } else if (sum < -2147483648L) {
                    return -2147483648;
                }
            } else {
                break;
            }
        }
        return (int)sum;
    }
}
