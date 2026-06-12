class Solution {
    static final int MOD = 1000000007;

    List<Integer>[] g;
    int[][] up;
    int[] depth;
    int LOG;
    long[] pow;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        dfs(1, 0);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        pow = new long[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow[i] = (pow[i - 1] * 2) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int lca = getLca(u, v);
            int dist = depth[u] + depth[v] - 2 * depth[lca];

            if (dist == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) pow[dist - 1];
            }
        }

        return ans;
    }

    private void dfs(int node, int parent) {
        up[node][0] = parent;

        for (int next : g[node]) {
            if (next != parent) {
                depth[next] = depth[node] + 1;
                dfs(next, node);
            }
        }
    }

    private int getLca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        int diff = depth[a] - depth[b];

        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) == 1) {
                a = up[a][j];
            }
        }

        if (a == b) {
            return a;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[a][j] != up[b][j]) {
                a = up[a][j];
                b = up[b][j];
            }
        }

        return up[a][0];
    }
}