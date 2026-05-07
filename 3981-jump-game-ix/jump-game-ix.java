class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];

        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], nums[i]);
        }

        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.min(suffix[i + 1], nums[i]);
        }

        int[] ans = new int[n];

        int start = 0;
        int best = nums[0];

        for (int i = 0; i < n - 1; i++) {
            best = Math.max(best, nums[i]);

            if (prefix[i] <= suffix[i + 1]) {
                for (int j = start; j <= i; j++) {
                    ans[j] = best;
                }

                start = i + 1;
                best = nums[start];
            }
        }

        best = nums[start];

        for (int i = start; i < n; i++) {
            best = Math.max(best, nums[i]);
        }

        for (int i = start; i < n; i++) {
            ans[i] = best;
        }

        return ans;
    }
}