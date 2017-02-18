class Solution {
public:
    string addStrings(string num1, string num2) {
        int carry = 0;
        string result = "";
        if (num2.length()>num1.length()) swap(num1, num2);
        reverse(num1.begin(), num1.end());
        reverse(num2.begin(), num2.end());
        int num1Length = num1.length();
        int num2Length = num2.length();
        stringbuf buf;
        for (int i=0; i<num1Length; i++) {
            int sum;
            if (i<num2Length) {
                sum = num1[i] - '0' + num2[i] - '0' + carry;
            } else {
                sum = num1[i] -'0' + carry;
            }
            carry = sum / 10;
            result += (sum % 10 + '0');
        }
        if (carry==1) result += '1';
        reverse(result.begin(), result.end());
        return result;
    }
};
