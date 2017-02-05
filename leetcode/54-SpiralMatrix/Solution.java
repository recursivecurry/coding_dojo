public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new ArrayList<>();
        int x = 0, y = 0, dir = 0;
        int xMin = 0, xMax = matrix[0].length-1, yMin = 0, yMax = matrix.length-1;
        List<Integer> result = new ArrayList<>();
        
        while(!end(x, y, xMin, xMax, yMin, yMax)) {
            result.add(matrix[y][x]);
            switch (dir) {
                case 0:
                    if (x+1 > xMax) {
                        yMin += 1;
                        y += 1;
                        dir = turn(dir);
                    } else {
                        x += 1;
                    }
                    break;
                case 1:
                    if (y+1 > yMax) {
                        xMax -= 1;
                        x -= 1;
                        dir = turn(dir);
                    } else {
                        y += 1;
                    }
                    break;
                case 2:
                    if (x-1 < xMin) {
                        yMax -= 1;
                        y -= 1;
                        dir = turn(dir);
                    } else {
                        x -= 1;
                    }
                    break;
                case 3:
                    if (y-1 < yMin) {
                        xMin += 1;
                        x += 1;
                        dir = turn(dir);
                    } else {
                        y -= 1;
                    }
                    break;
            }
        }
        return result;
    }
    
    private boolean end(int x, int y, int xMin, int xMax, int yMin, int yMax) {
        return x < xMin || x > xMax || y < yMin || y > yMax;
    }
    
    private int turn(int dir) {
        return (dir + 1) % 4;
    }
}
