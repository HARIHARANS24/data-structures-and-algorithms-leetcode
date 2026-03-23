class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] sMap = new int[256], tMap = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i), tc = t.charAt(i);
            if (sMap[sc] != tMap[tc]) return false;
            sMap[sc] = tMap[tc] = i + 1;
        }
        return true;
    }
}