import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();

        System.out.println(solve(n, m));

    }

    private static int solve(long n, long m) {
        if (n == m) {
            return 0;
        }
        int count = 0;
        long current = 2L;
        long previous = -1L;
        Map<Long,List<Long>> known = new HashMap<>();
        Map<Long,List<Long>> known2 = new HashMap<>();
        long primeMax = (long) Math.sqrt(m);
        long nextBegin = (primeMax+1 < n) ? n : primeMax+1;

        while (current<=primeMax) {
            if (known.containsKey(current)) {
                for (Long prime : known.get(current)) {
                    List<Long> primes = known.getOrDefault(current + prime, new ArrayList<>());
                    primes.add(prime);
                    known.put(current+prime, primes);
                }
                known.remove(current);
            } else {
                if (current <= primeMax) {
                    List<Long> primes = new ArrayList<>();
                    primes.add(current);
                    known.put(current * current, primes);
                    long next = (nextBegin+current-1)/current*current;
                    primes = known2.getOrDefault(next, new ArrayList<>());
                    primes.add(current);
                    known2.put(next, primes);
                }

                if (n <= current) {
                    if (previous+2 == current) {
                        count++;
                    }
                    previous = current;
                }
            }
            current++;
        }
        current = nextBegin;

        while (current<=m) {
            if (known2.containsKey(current)) {
                for (Long prime : known2.get(current)) {
                    List<Long> primes = known2.getOrDefault(current + prime, new ArrayList<>());
                    primes.add(prime);
                    known2.put(current + prime, primes);
                }
                known2.remove(current);
            } else {
                if (n <= current) {
                    if (previous+2 == current) {
                        count++;
                    }
                    previous = current;
                }
            }
            current++;
        }
        return count;
    }
}
