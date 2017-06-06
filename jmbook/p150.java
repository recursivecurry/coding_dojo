import java.util.Arrays;

public class Solution {

    private static boolean boggle(int width, int height, char[][] board, String word) {
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                if(check(x, y, width, height, board, word))
                    return true;
            }
        }
        return false;
    }

    private static final int[] DIRX = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] DIRY = {-1, -1, -1, 0, 0, 1, 1, 1};

    private static boolean check(int x, int y, int width, int height, char[][] board, String word) {

        if (word.isEmpty())
            return true;

        if (x < 0 || x >= width || y < 0 || y >= height )
            return false;

        if (board[y][x] != word.charAt(0))
            return false;

        for (int i=0; i<8; i++) {
            if (check(x+DIRX[i], y+DIRY[i], width, height, board, word.substring(1)))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] sample =
                {
                        {'u', 'r', 'l', 'p', 'm'},
                        {'x', 'p', 'r', 'e', 't'},
                        {'g', 'i', 'a', 'e', 't'},
                        {'x', 't', 'n', 'z', 'y'},
                        {'x', 'o', 'q', 'r', 's'}
                };
        assert(boggle(5, 5, sample, "girl")==true);
        assert(boggle(5, 5, sample, "love")==false);
    }
}
