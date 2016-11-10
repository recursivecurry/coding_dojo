public class EditDistance {

    public int minDistance(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();

        int[][] cache = new int[word1Length+1][word2Length+1];

        for (int i = 0; i <= word1Length; i++) {
            cache[i][0] = i;
        }

        for (int j = 0; j <= word2Length; j++) {
            cache[0][j] = j;
        }

        for (int i = 0; i < word1Length; i++) {
            char ch1 = word1.charAt(i);
            for (int j = 0; j < word2Length; j++) {
                char ch2 = word2.charAt(j);

                if (ch1 == ch2) {
                    cache[i+1][j+1] = cache[i][j];
                } else {
                    int insert = cache[i][j+1] + 1;
                    int replace = cache[i][j] + 1;
                    int delete = cache[i+1][j] + 1;

                    cache[i+1][j+1] = (insert > delete) ? (delete < replace ? delete : replace) : (insert < replace ? insert : replace);
                }
            }
        }
        return cache[word1Length][word2Length];
    }
}


