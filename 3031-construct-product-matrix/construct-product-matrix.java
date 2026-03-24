class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int mod = 12345;
        int n = grid.length, m = grid[0].length;
        int size = n * m;
        int[] arr = new int[size];
        int idx = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val % mod;
            }
        }
        int[] prefix = new int[size];
        int[] suffix = new int[size];
        prefix[0] = arr[0];
        for (int i = 1; i < size; i++) {
            prefix[i] = (int)((long)prefix[i - 1] * arr[i] % mod);
        }
        suffix[size - 1] = arr[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (int)((long)suffix[i + 1] * arr[i] % mod);
        }
        int[][] res = new int[n][m];
        for (int i = 0; i < size; i++) {
            int left = i > 0 ? prefix[i - 1] : 1;
            int right = i < size - 1 ? suffix[i + 1] : 1;
            int val = (int)((long)left * right % mod);
            res[i / m][i % m] = val;
        }
        return res;
    }
}