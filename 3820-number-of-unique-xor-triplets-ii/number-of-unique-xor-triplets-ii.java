class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;
        boolean[] dp = new boolean[MAX];
        dp[0] = true;

        for (int t = 0; t < 3; t++) {
            boolean[] next = new boolean[MAX];
            for (int x = 0; x < MAX; x++) {
                if (!dp[x]) continue;
                for (int v : nums) {
                    next[x ^ v] = true;
                }
            }
            dp = next;
        }

        int count = 0;
        for (boolean b : dp) {
            if (b) count++;
        }
        return count;
    }
}