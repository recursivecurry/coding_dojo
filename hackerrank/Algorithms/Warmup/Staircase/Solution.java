import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
        final int num = sc.nextInt();
        String result = IntStream.range(1, num+1)
                .mapToObj(
                        n ->
                                String.format(
                                        String.format("%%%ds", num),
                                        String.join("", Collections.nCopies(n, "#"))
                                )
                )
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }
}
