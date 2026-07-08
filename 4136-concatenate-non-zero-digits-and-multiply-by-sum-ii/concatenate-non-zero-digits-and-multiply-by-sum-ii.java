import java.util.*;

class Solution {
    static final int MOD = 1000000007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        ArrayList<Integer> posList = new ArrayList<>();
        ArrayList<Integer> digitList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                posList.add(i);
                digitList.add(d);
            }
        }

        int k = posList.size();
        int[] pos = new int[k + 1];
        long[] prefVal = new long[k + 1];
        long[] prefSum = new long[k + 1];
        long[] pow10 = new long[k + 1];
        pow10[0] = 1;

        for (int i = 1; i <= k; i++) {
            pos[i] = posList.get(i - 1);
            int d = digitList.get(i - 1);
            pow10[i] = (pow10[i - 1] * 10) % MOD;
            prefVal[i] = (prefVal[i - 1] * 10 + d) % MOD;
            prefSum[i] = prefSum[i - 1] + d;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, 1, k, l);
            int right = upperBound(pos, 1, k, r) - 1;

            if (left > right || left > k || right < 1) {
                ans[i] = 0;
                continue;
            }

            int len = right - left + 1;
            long x = (prefVal[right] - (prefVal[left - 1] * pow10[len]) % MOD + MOD) % MOD;
            long sum = prefSum[right] - prefSum[left - 1];
            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int l, int r, int target) {
        int ans = r + 1;
        while (l <= r) {
            int m = (l + r) >>> 1;
            if (arr[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    private int upperBound(int[] arr, int l, int r, int target) {
        int ans = r + 1;
        while (l <= r) {
            int m = (l + r) >>> 1;
            if (arr[m] > target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}