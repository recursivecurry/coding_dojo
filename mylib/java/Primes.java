import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Primes {
    private Map<Long,List<Long>> known = new HashMap<>();
    private long current = 2;

    public long next() {
        while (known.containsKey(current)) {
            for (Long prime : known.get(current)) {
                List<Long> primes = known.getOrDefault(current+prime, new ArrayList<>());
                primes.add(prime);
                known.put(current+prime, primes);
            }
            known.remove(current);
            current++;
        }
        known.put(current * current, new ArrayList<Long>(){{add(current);}});
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
