import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int[] arr = new int[m * n];
        int idx = 0;

        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val;
            }
        }

        int mod = arr[0] % x;
        for (int v : arr) {
            if (v % x != mod) return -1;
        }

        Arrays.sort(arr);
        int median = arr[arr.length / 2];

        int ops = 0;
        for (int v : arr) {
            ops += Math.abs(v - median) / x;
        }

        return ops;
    }
}