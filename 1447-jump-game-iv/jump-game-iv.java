class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];

        q.offer(0);
        vis[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int sz = q.size();

            while (sz-- > 0) {
                int i = q.poll();

                if (i == n - 1) return steps;

                if (i - 1 >= 0 && !vis[i - 1]) {
                    vis[i - 1] = true;
                    q.offer(i - 1);
                }

                if (i + 1 < n && !vis[i + 1]) {
                    vis[i + 1] = true;
                    q.offer(i + 1);
                }

                List<Integer> same = map.get(arr[i]);

                if (same != null) {
                    for (int idx : same) {
                        if (!vis[idx]) {
                            vis[idx] = true;
                            q.offer(idx);
                        }
                    }
                    map.remove(arr[i]);
                }
            }

            steps++;
        }

        return -1;
    }
}