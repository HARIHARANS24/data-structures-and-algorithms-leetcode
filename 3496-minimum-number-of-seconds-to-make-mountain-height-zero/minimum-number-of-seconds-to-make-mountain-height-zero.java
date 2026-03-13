class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 0, right = (long)1e18;
        
        while (left < right) {
            long mid = (left + right) / 2;
            long total = 0;
            
            for (int t : workerTimes) {
                long val = 1 + (8 * mid) / t;
                long x = (long)((Math.sqrt(val) - 1) / 2);
                total += x;
                if (total >= mountainHeight) break;
            }
            
            if (total >= mountainHeight) right = mid;
            else left = mid + 1;
        }
        
        return left;
    }
}