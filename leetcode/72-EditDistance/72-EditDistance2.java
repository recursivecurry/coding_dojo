public class EditDistance2 {
    private int[][] cache;
    private String word1;
    private String word2;

    private int get(int i, int j) {
        if (cache[i][j] != -1)
            return cache[i][j];

        char ch1 = word1.charAt(i-1);
        char ch2 = word2.charAt(j-1);

        if (ch1 == ch2) {
            cache[i][j] = get(i-1, j-1);
        } else {
            int insert = get(i-1, j) + 1;
            int replace = get(i-1, j-1) + 1;
            int delete = get(i, j-1) + 1;

            cache[i][j] = (insert > delete) ? (delete < replace ? delete : replace) : (insert < replace ? insert : replace);
        }
        return cache[i][j];
    }

    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;

        this.cache = new int[word1.length()+1][word2.length()+1];

        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0) {
                    cache[i][j] = j;
                } else if (j == 0) {
                    cache[i][j] = i;
                } else {
                    cache[i][j] = -1;
                }
            }
        }


        return get(word1.length(), word2.length());
    }
}
