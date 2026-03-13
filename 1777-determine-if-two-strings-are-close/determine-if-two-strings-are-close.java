class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int[] a = new int[26];
        int[] b = new int[26];

        for (char c : word1.toCharArray()) a[c - 'a']++;
        for (char c : word2.toCharArray()) b[c - 'a']++;

        for (int i = 0; i < 26; i++) {
            if ((a[i] == 0) != (b[i] == 0)) return false;
        }

        java.util.Arrays.sort(a);
        java.util.Arrays.sort(b);

        return java.util.Arrays.equals(a, b);
    }
}