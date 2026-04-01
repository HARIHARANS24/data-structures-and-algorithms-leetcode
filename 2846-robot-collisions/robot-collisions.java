import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        int[][] robots = new int[n][4];
        
        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = directions.charAt(i);
            robots[i][3] = i;
        }
        
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            if (robots[i][2] == 'R') {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && robots[i][1] > 0) {
                    int j = stack.peek();
                    if (robots[j][1] < robots[i][1]) {
                        stack.pop();
                        robots[i][1]--;
                        robots[j][1] = 0;
                    } else if (robots[j][1] == robots[i][1]) {
                        stack.pop();
                        robots[j][1] = 0;
                        robots[i][1] = 0;
                        break;
                    } else {
                        robots[j][1]--;
                        robots[i][1] = 0;
                        break;
                    }
                }
            }
        }
        
        List<int[]> survivors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (robots[i][1] > 0) {
                survivors.add(new int[]{robots[i][3], robots[i][1]});
            }
        }
        
        Collections.sort(survivors, (a, b) -> a[0] - b[0]);
        
        List<Integer> result = new ArrayList<>();
        for (int[] s : survivors) {
            result.add(s[1]);
        }
        
        return result;
    }
}