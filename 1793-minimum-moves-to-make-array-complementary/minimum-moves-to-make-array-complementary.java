class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;

        // difference array for tracking move counts
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];

            int low = Math.min(a, b);
            int high = Math.max(a, b);

            int sum = a + b;

            /*
                For every pair:
                
                2 moves initially for all sums
                1 move for range [low + 1, high + limit]
                0 move exactly at sum
            */

            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            diff[low + 1] -= 1;
            diff[high + limit + 1] += 1;

            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }

        int answer = Integer.MAX_VALUE;
        int current = 0;

        for (int target = 2; target <= 2 * limit; target++) {
            current += diff[target];
            answer = Math.min(answer, current);
        }

        return answer;
    }
}