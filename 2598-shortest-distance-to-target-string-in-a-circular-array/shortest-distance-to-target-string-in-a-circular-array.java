class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int dist = Math.min(Math.abs(i - startIndex), n - Math.abs(i - startIndex));
                ans = Math.min(ans, dist);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}