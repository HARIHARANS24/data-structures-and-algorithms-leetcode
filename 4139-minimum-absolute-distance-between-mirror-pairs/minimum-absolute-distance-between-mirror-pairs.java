import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> lastIndex = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (lastIndex.containsKey(nums[i])) {
                ans = Math.min(ans, i - lastIndex.get(nums[i]));
            }

            int rev = reverse(nums[i]);
            lastIndex.put(rev, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int reverse(int x) {
        int rev = 0;

        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return rev;
    }
}