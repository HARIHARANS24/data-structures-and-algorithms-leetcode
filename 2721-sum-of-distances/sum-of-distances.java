import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, long[]> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            long[] val = map.getOrDefault(nums[i], new long[2]);
            res[i] = val[0] * i - val[1];
            val[0]++;
            val[1] += i;
            map.put(nums[i], val);
        }
        
        map.clear();
        
        for (int i = n - 1; i >= 0; i--) {
            long[] val = map.getOrDefault(nums[i], new long[2]);
            res[i] += val[1] - val[0] * i;
            val[0]++;
            val[1] += i;
            map.put(nums[i], val);
        }
        
        return res;
    }
}