import java.util.*;

class Solution {

    public int maxDistance(int side, int[][] points, int k) {
        List<int[]> list = build(points, side);

        int low = 0, high = side;

        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (can(list, k, mid)) low = mid;
            else high = mid - 1;
        }

        return low;
    }

    boolean can(List<int[]> list, int k, int d) {
        int n = list.size();
        int best = 1;

        Deque<int[]> dq = new ArrayDeque<>();
        dq.addLast(new int[]{list.get(0)[0], list.get(0)[1], list.get(0)[0], list.get(0)[1], 1});

        for (int i = 1; i < n; i++) {
            int x = list.get(i)[0];
            int y = list.get(i)[1];

            int sx = x, sy = y, len = 1;

            while (!dq.isEmpty()) {
                int[] cur = dq.peekFirst();

                int distEnd = Math.abs(x - cur[2]) + Math.abs(y - cur[3]);
                if (distEnd < d) break;

                int distStart = Math.abs(x - cur[0]) + Math.abs(y - cur[1]);
                if (distStart >= d && cur[4] + 1 >= len) {
                    sx = cur[0];
                    sy = cur[1];
                    len = cur[4] + 1;
                    best = Math.max(best, len);
                }

                dq.pollFirst();
            }

            dq.addLast(new int[]{sx, sy, x, y, len});
        }

        return best >= k;
    }

    List<int[]> build(int[][] points, int side) {
        List<int[]> left = new ArrayList<>();
        List<int[]> top = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        List<int[]> bottom = new ArrayList<>();

        for (int[] p : points) {
            int x = p[0], y = p[1];

            if (x == 0 && y > 0) left.add(p);
            else if (y == side && x > 0) top.add(p);
            else if (x == side && y < side) right.add(p);
            else bottom.add(p);
        }

        left.sort((a, b) -> a[1] - b[1]);
        top.sort((a, b) -> a[0] - b[0]);
        right.sort((a, b) -> b[1] - a[1]);
        bottom.sort((a, b) -> b[0] - a[0]);

        List<int[]> res = new ArrayList<>();
        res.addAll(left);
        res.addAll(top);
        res.addAll(right);
        res.addAll(bottom);

        return res;
    }
}