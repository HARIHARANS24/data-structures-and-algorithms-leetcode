import java.math.BigInteger;

class Solution {
    static final BigInteger LIMIT = BigInteger.valueOf(1000000);

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int[] half = new int[26];
        char mid = 0;
        int m = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            m += half[i];
            if ((freq[i] & 1) == 1) mid = (char) ('a' + i);
        }

        if (countWays(half, m).compareTo(BigInteger.valueOf(k)) < 0) return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < m; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                BigInteger ways = countWays(half, m - pos - 1);
                if (ways.compareTo(BigInteger.valueOf(k)) >= 0) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways.intValue();
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder(left);
        if (mid != 0) ans.append(mid);
        ans.append(new StringBuilder(left).reverse());
        return ans.toString();
    }

    private BigInteger countWays(int[] cnt, int rem) {
        BigInteger res = BigInteger.ONE;
        int used = 0;
        for (int x : cnt) {
            if (x == 0) continue;
            res = res.multiply(comb(used + x, x));
            if (res.compareTo(LIMIT) > 0) return LIMIT;
            used += x;
        }
        return res;
    }

    private BigInteger comb(int n, int r) {
        if (r < 0 || r > n) return BigInteger.ZERO;
        r = Math.min(r, n - r);
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= r; i++) {
            res = res.multiply(BigInteger.valueOf(n - r + i));
            res = res.divide(BigInteger.valueOf(i));
            if (res.compareTo(LIMIT) > 0) return LIMIT;
        }
        return res;
    }
}