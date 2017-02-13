import java.util.regex.Pattern;
public class Solution {
    private static final Pattern rows[] = {Pattern.compile("[qwertyuiop]+", Pattern.CASE_INSENSITIVE),
            Pattern.compile("[asdfghjkl]+", Pattern.CASE_INSENSITIVE),
            Pattern.compile("[zxcvbnm]+", Pattern.CASE_INSENSITIVE)};
    public String[] findWords(String[] words) {
        return Arrays.stream(words)
                .filter(s ->
                        Arrays.stream(rows)
                        .anyMatch(p -> p.matcher(s).matches())
                )
                .toArray(String[]::new);
    }
}
