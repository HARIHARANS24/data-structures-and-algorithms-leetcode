import java.util.*;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            int left = 0, right = m - 1;
            int idx = m;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if ((long) spells[i] * potions[mid] >= success) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            res[i] = m - idx;
        }
        
        return res;
    }
}