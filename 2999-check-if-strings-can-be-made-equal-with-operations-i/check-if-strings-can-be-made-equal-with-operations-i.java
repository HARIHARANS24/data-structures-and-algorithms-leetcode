class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char[] a1 = {s1.charAt(0), s1.charAt(2)};
        char[] b1 = {s1.charAt(1), s1.charAt(3)};
        char[] a2 = {s2.charAt(0), s2.charAt(2)};
        char[] b2 = {s2.charAt(1), s2.charAt(3)};
        java.util.Arrays.sort(a1);
        java.util.Arrays.sort(b1);
        java.util.Arrays.sort(a2);
        java.util.Arrays.sort(b2);
        return java.util.Arrays.equals(a1, a2) && java.util.Arrays.equals(b1, b2);
    }
}