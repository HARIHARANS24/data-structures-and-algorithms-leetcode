class Solution {
    int count = 0;
    String ans = "";

    public String getHappyString(int n, int k) {
        dfs("", n, k);
        return ans;
    }

    void dfs(String cur, int n, int k) {
        if (cur.length() == n) {
            count++;
            if (count == k) ans = cur;
            return;
        }

        for (char c : new char[]{'a','b','c'}) {
            if (cur.length() > 0 && cur.charAt(cur.length() - 1) == c) continue;
            dfs(cur + c, n, k);
            if (!ans.equals("")) return;
        }
    }
}