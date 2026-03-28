class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] res = new char[n];
        for (int i = 0; i < n; i++) res[i] = '#';
        char c = 'a';
        for (int i = 0; i < n; i++) {
            if (res[i] != '#') continue;
            if (c > 'z') return "";
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) res[j] = c;
            }
            c++;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expected = 0;
                if (res[i] == res[j]) {
                    if (i == n - 1 || j == n - 1) expected = 1;
                    else expected = lcp[i + 1][j + 1] + 1;
                }
                if (lcp[i][j] != expected) return "";
            }
        }
        return new String(res);
    }
}