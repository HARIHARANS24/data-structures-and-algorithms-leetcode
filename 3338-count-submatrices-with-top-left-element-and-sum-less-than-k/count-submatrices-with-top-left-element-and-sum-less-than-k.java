class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        long[][] prefix = new long[m][n];
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long up = i > 0 ? prefix[i - 1][j] : 0;
                long left = j > 0 ? prefix[i][j - 1] : 0;
                long diag = (i > 0 && j > 0) ? prefix[i - 1][j - 1] : 0;
                prefix[i][j] = grid[i][j] + up + left - diag;
                if (prefix[i][j] <= k) count++;
            }
        }
        
        return count;
    }
}