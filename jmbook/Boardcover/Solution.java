import java.util.Scanner;

class Solution {

    static final int[][][] PATTERNS = {
            {{0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {0, 1}, {-1, 1}}};

    static final char WHITE = '.';
    static final char BLACK = '#';

    static boolean insert(int x, int y, int width, int height, char[][] map, int[][] pattern) {
        for (int i = 0; i < 3; i++) {
            int nx = x + pattern[i][0];
            int ny = y + pattern[i][1];
            if (nx < 0 || nx >= width || ny < 0 || ny >= height || map[ny][nx] == BLACK) {
                return false;
            }
        }
        for (int i = 0; i < pattern.length; i++) {
            map[y + pattern[i][1]][x + pattern[i][0]] = BLACK;
        }
        return true;
    }

    static boolean remove(int x, int y, char[][] map, int[][] pattern) {
        if (map[y+pattern[0][1]][x+pattern[0][0]] == BLACK
                && map[y+pattern[1][1]][x+pattern[1][0]] == BLACK
                && map[y+pattern[2][1]][x+pattern[2][0]] == BLACK) {
            for (int i = 0; i < pattern.length; i++) {
                map[y+pattern[i][1]][x+pattern[i][0]] = WHITE;
            }
            return true;
        }
        else
            return false;
    }

    public static int solve(int x, int y, int width, int height, char[][] map) {
        if (x == width) {
            y++;
            x = 0;
        }
        if (y == height) {
            return 1;
        }

        if (map[y][x] == BLACK) {
            return solve(x+1, y, width, height, map);
        }

        int total = 0;

        for (int i = 0; i< PATTERNS.length; i++) {
            if (insert(x, y, width, height, map, PATTERNS[i])) {
                total += solve(x+1, y, width, height, map);
                remove(x, y, map, PATTERNS[i]);
            }
        }
        return total;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int height = sc.nextInt();
            int width = sc.nextInt();
            sc.nextLine();
            char[][] map = new char[height][];
            for (int j = 0; j < height; j++) {
                map[j] = sc.nextLine().toCharArray();
            }
            System.out.println(solve(0, 0, width, height, map));
        }
    }
}

//3
//3 7
//#.....#
//#.....#
//##...##
//3 7
//#.....#
//#.....#
//##..###
//8 10
//##########
//#........#
//#........#
//#........#
//#........#
//#........#
//#........#
//##########

