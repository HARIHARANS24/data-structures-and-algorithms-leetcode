class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        
        while (a > 0 || b > 0 || c > 0) {
            int bitA = a & 1;
            int bitB = b & 1;
            int bitC = c & 1;
            
            if ((bitA | bitB) != bitC) {
                if (bitC == 1) {
                    flips += 1; // need one of them to be 1
                } else {
                    flips += bitA + bitB; // both must be 0
                }
            }
            
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        
        return flips;
    }
}