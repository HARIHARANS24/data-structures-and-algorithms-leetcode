import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long ans = Long.MAX_VALUE;

        int n = landStartTime.length;
        int[] landEnd = new int[n];
        for (int i = 0; i < n; i++) {
            landEnd[i] = landStartTime[i] + landDuration[i];
        }
        ans = Math.min(ans, solve(landEnd, waterStartTime, waterDuration));

        int m = waterStartTime.length;
        int[] waterEnd = new int[m];
        for (int j = 0; j < m; j++) {
            waterEnd[j] = waterStartTime[j] + waterDuration[j];
        }
        ans = Math.min(ans, solve(waterEnd, landStartTime, landDuration));

        return (int) ans;
    }

    private long solve(int[] finishTimes, int[] start, int[] duration) {
        int k = start.length;

        int[][] rides = new int[k][2];
        for (int i = 0; i < k; i++) {
            rides[i][0] = start[i];
            rides[i][1] = duration[i];
        }

        Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));

        int[] starts = new int[k];
        long[] prefMinDur = new long[k];
        long[] suffMinOpenFinish = new long[k];

        for (int i = 0; i < k; i++) {
            starts[i] = rides[i][0];
        }

        prefMinDur[0] = rides[0][1];
        for (int i = 1; i < k; i++) {
            prefMinDur[i] = Math.min(prefMinDur[i - 1], rides[i][1]);
        }

        suffMinOpenFinish[k - 1] = (long) rides[k - 1][0] + rides[k - 1][1];
        for (int i = k - 2; i >= 0; i--) {
            suffMinOpenFinish[i] = Math.min(
                suffMinOpenFinish[i + 1],
                (long) rides[i][0] + rides[i][1]
            );
        }

        long res = Long.MAX_VALUE;

        for (int t : finishTimes) {
            int idx = upperBound(starts, t); 

            long best = Long.MAX_VALUE;

            if (idx > 0) {
                best = Math.min(best, (long) t + prefMinDur[idx - 1]);
            }

            if (idx < k) {
                best = Math.min(best, suffMinOpenFinish[idx]);
            }

            res = Math.min(res, best);
        }

        return res;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}