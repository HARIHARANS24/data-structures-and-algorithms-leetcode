class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {
            int ls = landStartTime[i];
            int ld = landDuration[i];

            for (int j = 0; j < waterStartTime.length; j++) {
                int ws = waterStartTime[j];
                int wd = waterDuration[j];

                int landFirst = Math.max(ws, ls + ld) + wd;
                int waterFirst = Math.max(ls, ws + wd) + ld;

                ans = Math.min(ans, Math.min(landFirst, waterFirst));
            }
        }

        return ans;
    }
}