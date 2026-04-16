import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int[] best = new int[n];
        Arrays.fill(best, -1);
        for (List<Integer> list : map.values()) {
            int m = list.size();
            if (m == 1) continue;
            for (int i = 0; i < m; i++) {
                int curr = list.get(i);
                int prev = list.get((i - 1 + m) % m);
                int next = list.get((i + 1) % m);
                int d1 = Math.abs(curr - prev);
                int d2 = Math.abs(curr - next);
                d1 = Math.min(d1, n - d1);
                d2 = Math.min(d2, n - d2);
                best[curr] = Math.min(d1, d2);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int q : queries) {
            res.add(best[q]);
        }
        return res;
    }
}