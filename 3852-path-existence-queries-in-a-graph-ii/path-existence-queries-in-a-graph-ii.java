import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] pos = new int[n];
        int[] comp = new int[n];
        int cid = 0;
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
            if (i > 0 && arr[i][0] - arr[i - 1][0] > maxDiff) cid++;
            comp[i] = cid;
        }

        int[] next = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && arr[r + 1][0] - arr[i][0] <= maxDiff) r++;
            next[i] = r;
        }

        int[] prev = new int[n];
        int l = 0;
        for (int i = 0; i < n; i++) {
            while (arr[i][0] - arr[l][0] > maxDiff) l++;
            prev[i] = l;
        }

        int LOG = 18;
        int[][] upF = new int[LOG][n];
        int[][] upB = new int[LOG][n];
        for (int i = 0; i < n; i++) {
            upF[0][i] = next[i];
            upB[0][i] = prev[i];
        }
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                upF[k][i] = upF[k - 1][upF[k - 1][i]];
                upB[k][i] = upB[k - 1][upB[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int u = pos[queries[qi][0]];
            int v = pos[queries[qi][1]];
            if (u == v) {
                ans[qi] = 0;
                continue;
            }
            if (comp[u] != comp[v]) {
                ans[qi] = -1;
                continue;
            }
            if (u < v) {
                int cur = u;
                int res = 0;
                for (int k = LOG - 1; k >= 0; k--) {
                    int nx = upF[k][cur];
                    if (nx < v) {
                        cur = nx;
                        res += 1 << k;
                    }
                }
                ans[qi] = res + 1;
            } else {
                int cur = u;
                int res = 0;
                for (int k = LOG - 1; k >= 0; k--) {
                    int nx = upB[k][cur];
                    if (nx > v) {
                        cur = nx;
                        res += 1 << k;
                    }
                }
                ans[qi] = res + 1;
            }
        }
        return ans;
    }
}