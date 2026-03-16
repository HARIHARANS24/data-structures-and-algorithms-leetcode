import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        
        int[] count = new int[128];
        for (char c : t.toCharArray()) count[c]++;
        
        int left = 0, right = 0, required = t.length();
        int minLen = Integer.MAX_VALUE, start = 0;
        
        while (right < s.length()) {
            if (count[s.charAt(right)] > 0) required--;
            count[s.charAt(right)]--;
            right++;
            
            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                
                count[s.charAt(left)]++;
                if (count[s.charAt(left)] > 0) required++;
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}