import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];
        int maxCost = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            if (e[2] > maxCost) {
                maxCost = e[2];
            }
        }

        int[] topo = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : graph[u]) {
                indegree[e[0]]--;
                if (indegree[e[0]] == 0) {
                    q.offer(e[0]);
                }
            }
        }

        if (!check(0, graph, topo, online, k, n)) {
            return -1;
        }

        int left = 0;
        int right = maxCost;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, graph, topo, online, k, n)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(int limit, ArrayList<int[]>[] graph, int[] topo, boolean[] online, long k, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == Long.MAX_VALUE) {
                continue;
            }
            if (u != 0 && u != n - 1 && !online[u]) {
                continue;
            }
            for (int[] e : graph[u]) {
                int v = e[0];
                int cost = e[1];
                if (cost < limit) {
                    continue;
                }
                if (v != 0 && v != n - 1 && !online[v]) {
                    continue;
                }
                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}