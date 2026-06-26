class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int offset = n + 2;
        int size = 2 * n + 5;
        long ans = 0;
        int[] bit = new int[size];
        int prefix = 0;

        update(bit, offset, 1);

        for (int x : nums) {
            prefix += (x == target) ? 1 : -1;
            int idx = prefix + offset;
            ans += query(bit, idx - 1);
            update(bit, idx, 1);
        }

        return ans;
    }

    private void update(int[] bit, int idx, int val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    private int query(int[] bit, int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }
}