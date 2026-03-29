class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        for (int a : asteroids) {
            boolean alive = true;
            
            while (alive && a < 0 && !stack.isEmpty() && stack.peek() > 0) {
                if (stack.peek() < -a) {
                    stack.pop();
                } else if (stack.peek() == -a) {
                    stack.pop();
                    alive = false;
                } else {
                    alive = false;
                }
            }
            
            if (alive) stack.push(a);
        }
        
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        
        return res;
    }
}