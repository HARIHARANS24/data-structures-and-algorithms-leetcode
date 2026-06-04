class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;
        
        for (int x = num1; x <= num2; x++) {
            char[] d = String.valueOf(x).toCharArray();
            int n = d.length;
            
            for (int i = 1; i < n - 1; i++) {
                if ((d[i] > d[i - 1] && d[i] > d[i + 1]) ||
                    (d[i] < d[i - 1] && d[i] < d[i + 1])) {
                    total++;
                }
            }
        }
        
        return total;
    }
}