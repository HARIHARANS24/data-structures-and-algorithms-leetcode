import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                set.add(grid[r][c]);
                int maxSize = Math.min(Math.min(r, m - 1 - r), Math.min(c, n - 1 - c));
                for (int k = 1; k <= maxSize; k++) {
                    int sum = 0;
                    int x = r - k, y = c;
                    for (int i = 0; i < k; i++) sum += grid[x + i][y + i];
                    x = r; y = c + k;
                    for (int i = 0; i < k; i++) sum += grid[x + i][y - i];
                    x = r + k; y = c;
                    for (int i = 0; i < k; i++) sum += grid[x - i][y - i];
                    x = r; y = c - k;
                    for (int i = 0; i < k; i++) sum += grid[x - i][y + i];
                    set.add(sum);
                }
            }
        }

        int size = Math.min(3, set.size());
        int[] res = new int[size];
        int i = 0;
        for (int val : set) {
            if (i == size) break;
            res[i++] = val;
        }
        return res;
    }
}