import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        for (int[] o : obstacles) {
            set.add(o[0] + "," + o[1]);
        }
        
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int dir = 0, x = 0, y = 0, max = 0;
        
        for (int c : commands) {
            if (c == -1) {
                dir = (dir + 1) % 4;
            } else if (c == -2) {
                dir = (dir + 3) % 4;
            } else {
                for (int i = 0; i < c; i++) {
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];
                    if (set.contains(nx + "," + ny)) break;
                    x = nx;
                    y = ny;
                    max = Math.max(max, x * x + y * y);
                }
            }
        }
        
        return max;
    }
}