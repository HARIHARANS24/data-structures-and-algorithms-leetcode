class Solution {
    public int minimumEffort(int[][] tasks) {

        // Tasks needing larger extra buffer should go first
        Arrays.sort(tasks, (a, b) -> {
            int diff1 = a[1] - a[0];
            int diff2 = b[1] - b[0];
            return diff2 - diff1;
        });

        int energy = 0;
        int current = 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            // If current energy is not enough to start this task,
            // increase initial energy accordingly
            if (current < minimum) {
                energy += (minimum - current);
                current = minimum;
            }

            current -= actual;
        }

        return energy;
    }
}