class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        long[] freq = new long[max + 1];
        for (int x : nums) freq[x]++;

        long[] exact = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            long count = 0;
            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            exact[g] = count * (count - 1) / 2;

            for (int multiple = g + g; multiple <= max; multiple += g) {
                exact[g] -= exact[multiple];
            }
        }

        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exact[g];
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int left = 1, right = max;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (prefix[mid] > q) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer[i] = left;
        }

        return answer;
    }
}