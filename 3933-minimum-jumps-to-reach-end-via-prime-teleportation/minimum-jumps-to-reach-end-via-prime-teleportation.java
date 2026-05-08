import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] spf = new int[max + 1];

        for (int i = 2; i <= max; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                if ((long) i * i <= max) {
                    for (int j = i * i; j <= max; j += i) {
                        if (spf[j] == 0) spf[j] = i;
                    }
                }
            }
        }

        Map<Integer, ArrayList<Integer>> divisible = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            while (x > 1) {
                int p = spf[x];

                divisible.computeIfAbsent(p, k -> new ArrayList<>()).add(i);

                while (x % p == 0) x /= p;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        boolean[] usedPrime = new boolean[max + 1];

        q.offer(0);
        vis[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int idx = q.poll();

                if (idx == n - 1) return steps;

                if (idx - 1 >= 0 && !vis[idx - 1]) {
                    vis[idx - 1] = true;
                    q.offer(idx - 1);
                }

                if (idx + 1 < n && !vis[idx + 1]) {
                    vis[idx + 1] = true;
                    q.offer(idx + 1);
                }

                int val = nums[idx];

                if (val > 1 && spf[val] == val && !usedPrime[val]) {
                    usedPrime[val] = true;

                    ArrayList<Integer> list = divisible.get(val);

                    if (list != null) {
                        for (int next : list) {
                            if (!vis[next]) {
                                vis[next] = true;
                                q.offer(next);
                            }
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}