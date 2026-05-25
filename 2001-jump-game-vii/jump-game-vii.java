class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] vis = new boolean[n];
        vis[0] = true;

        int far = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) continue;

            int l = Math.max(i + minJump, far + 1);
            int r = Math.min(i + maxJump, n - 1);

            for (int j = l; j <= r; j++) {
                if (s.charAt(j) == '0') {
                    vis[j] = true;
                    if (j == n - 1) return true;
                }
            }

            far = Math.max(far, r);
        }

        return n == 1;
    }
}