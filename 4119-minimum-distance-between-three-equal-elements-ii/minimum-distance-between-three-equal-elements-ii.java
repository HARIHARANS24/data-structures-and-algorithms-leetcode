import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int res = Integer.MAX_VALUE;

        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;
            for (int i = 1; i < list.size() - 1; i++) {
                int a = list.get(i - 1);
                int b = list.get(i);
                int c = list.get(i + 1);
                int dist = 2 * (c - a);
                res = Math.min(res, dist);
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}