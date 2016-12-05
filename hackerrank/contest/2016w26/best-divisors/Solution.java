import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private static int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }

    private static int comparator(int n1, int n2) {
        int s1 = digitSum(n1), s2 = digitSum(n2);
        if (s1 > s2) {
            return -1;
        } else if (s1 < s2) {
            return 1;
        } else {
            return n1 - n2;
        }
    }
    private static int best(int num) {
        int prime = 2;
        Set<Integer> divisors = new TreeSet<>(Solution::comparator);
        divisors.add(1);

        while (num > 1) {
            final int finalPrime = prime;
            while (num % finalPrime == 0) {
                Collection<Integer> newDivisors =
                        divisors.stream().map(n->n*finalPrime).collect(Collectors.toList());
                divisors.addAll(newDivisors);
                num /= finalPrime;
            }
            prime++;
        }

        return divisors.iterator().next();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(best(n));
    }
}
