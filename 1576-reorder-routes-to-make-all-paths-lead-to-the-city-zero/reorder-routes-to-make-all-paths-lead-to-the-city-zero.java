import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for (int[] c : connections) {
            graph[c[0]].add(new int[]{c[1], 1});
            graph[c[1]].add(new int[]{c[0], 0});
        }
        
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        
        int res = 0;
        
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int[] nei : graph[node]) {
                int next = nei[0], cost = nei[1];
                if (!visited[next]) {
                    visited[next] = true;
                    res += cost;
                    q.offer(next);
                }
            }
        }
        
        return res;
    }
}