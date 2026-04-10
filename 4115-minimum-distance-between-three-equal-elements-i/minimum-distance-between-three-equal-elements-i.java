import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int res = Integer.MAX_VALUE;
        
        for (List<Integer> list : map.values()) {
            int size = list.size();
            if (size < 3) continue;
            
            for (int i = 0; i < size - 2; i++) {
                int a = list.get(i);
                int b = list.get(i + 1);
                int c = list.get(i + 2);
                int dist = Math.abs(a - b) + Math.abs(b - c) + Math.abs(c - a);
                res = Math.min(res, dist);
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}