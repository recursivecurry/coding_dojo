import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Primes {
    private Map<Integer,List<Integer>> known = new HashMap<>();
    private int current = 2;

    public int next() {
        while (known.containsKey(current)) {
            for (Integer prime : known.get(current)) {
                known.compute(current + prime,
                        (k, v) -> {
                            if (v == null) {
                                List<Integer> primeList = new ArrayList<>();
                                primeList.add(prime);
                                return primeList;
                            } else {
                                v.add(prime);
                                return v;
                            }
                        });
            }
            current++;
        }
        List<Integer> thisPrimes = new ArrayList<>();
        thisPrimes.add(current);
        known.put(current * current, thisPrimes);
        return current++;
    }

    public static void main(String[] args) {
        Primes primes = new Primes();
        System.out.println(primes.next());
        System.out.println(primes.next());
        System.out.println(primes.next());
        System.out.println(primes.next());
        System.out.println(primes.next());
        System.out.println(primes.next());
        System.out.println(primes.next());
    }
}
