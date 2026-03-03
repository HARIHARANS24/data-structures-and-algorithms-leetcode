import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[128];
        Arrays.fill(index, -1);

        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (index[c] >= left) {
                left = index[c] + 1;
            }

            index[c] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}