class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            long cur = len[i];

            if (c >= 'a' && c <= 'z') {
                len[i + 1] = cur + 1;
            } else if (c == '*') {
                len[i + 1] = cur > 0 ? cur - 1 : 0;
            } else if (c == '#') {
                len[i + 1] = cur * 2;
            } else {
                len[i + 1] = cur;
            }
        }

        if (k < 0 || k >= len[n]) return '.';

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long prev = len[i];

            if (c >= 'a' && c <= 'z') {
                if (k == prev) return c;
            } else if (c == '#') {
                if (k >= prev) k -= prev;
            } else if (c == '%') {
                k = prev - 1 - k;
            }
        }

        return '.';
    }
}