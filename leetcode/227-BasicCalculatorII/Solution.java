import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


@Slf4j
public class Solution {

    public int calculate(String s) {
        s = s + "+0";
        int result = 0;
        int previous = 0;
        int number = 0;
        char operator=' ';
        for (int i=0; i<s.length(); i++) {
            char current = s.charAt(i);
            if (current == ' ') {
                continue;
            } else if (Character.isDigit(current)) {
                number = (number * 10 + (current - '0'));
            } else {
                if (operator == '*') {
                    previous = previous * number;
                } else if (operator == '/') {
                    previous = previous / number;
                } else if (operator == '+') {
                    result = result + previous;
                    previous = number;
                } else if (operator == '-') {
                    result = result + previous;
                    previous = -number;
                } else {
                    previous = number;
                }
                number = 0;
                operator = current;
            }
        }
        return result + previous;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("1-1"));
        System.out.println(solution.calculate("0-2147483647"));
        System.out.println(solution.calculate("3+2*2"));
        System.out.println(solution.calculate(" 3/2"));
        System.out.println(solution.calculate(" 3+5 / 2"));
    }
}
