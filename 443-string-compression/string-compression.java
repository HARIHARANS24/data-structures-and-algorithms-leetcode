class Solution {
    public int compress(char[] chars) {
        int i = 0, idx = 0;
        while (i < chars.length) {
            char c = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == c) {
                i++;
                count++;
            }
            chars[idx++] = c;
            if (count > 1) {
                char[] num = String.valueOf(count).toCharArray();
                for (char n : num) chars[idx++] = n;
            }
        }
        return idx;
    }
}