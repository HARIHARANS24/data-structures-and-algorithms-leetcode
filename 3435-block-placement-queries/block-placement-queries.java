class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int LIM = 50001;

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(LIM);

        for (int[] q : queries) {
            if (q[0] == 1) set.add(q[1]);
        }

        SegTree seg = new SegTree(LIM + 1);

        int prev = -1;
        for (int x : set) {
            if (prev != -1) seg.update(x, x - prev);
            prev = x;
        }

        ArrayList<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];

            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];

                int best = seg.query(0, x);

                Integer p = set.floor(x);
                best = Math.max(best, x - p);

                ans.add(best >= sz);
            } else {
                int x = q[1];

                int l = set.lower(x);
                int r = set.higher(x);

                seg.update(r, r - l);
                seg.update(x, 0);

                set.remove(x);
            }
        }

        Collections.reverse(ans);
        return ans;
    }

    static class SegTree {
        int n;
        int[] st;

        SegTree(int m) {
            n = 1;
            while (n < m) n <<= 1;
            st = new int[n << 1];
        }

        void update(int pos, int val) {
            int p = pos + n;
            st[p] = val;

            for (p >>= 1; p > 0; p >>= 1) {
                st[p] = Math.max(st[p << 1], st[p << 1 | 1]);
            }
        }

        int query(int l, int r) {
            int res = 0;

            for (l += n, r += n; l <= r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1) res = Math.max(res, st[l++]);
                if ((r & 1) == 0) res = Math.max(res, st[r--]);
            }

            return res;
        }
    }
}