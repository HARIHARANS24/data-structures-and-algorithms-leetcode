class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;
        int n = encodedText.length();
        int cols = n / rows;
        char[][] mat = new char[rows][cols];
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = encodedText.charAt(idx++);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < cols; j++) {
            int i = 0, k = j;
            while (i < rows && k < cols) {
                res.append(mat[i][k]);
                i++;
                k++;
            }
        }
        int end = res.length() - 1;
        while (end >= 0 && res.charAt(end) == ' ') end--;
        return res.substring(0, end + 1);
    }
}