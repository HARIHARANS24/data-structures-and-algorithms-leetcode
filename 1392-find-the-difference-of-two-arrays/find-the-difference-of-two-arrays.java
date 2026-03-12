import java.util.*;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();

        for (int n : nums1) s1.add(n);
        for (int n : nums2) s2.add(n);

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int n : s1) {
            if (!s2.contains(n)) a.add(n);
        }

        for (int n : s2) {
            if (!s1.contains(n)) b.add(n);
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(a);
        res.add(b);

        return res;
    }
}