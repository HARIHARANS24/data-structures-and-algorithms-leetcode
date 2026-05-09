class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        int layers = Math.min(rows, cols) / 2;

        for (int layer = 0; layer < layers; layer++) {

            List<Integer> vals = new ArrayList<>();

            int top = layer;
            int left = layer;
            int bottom = rows - layer - 1;
            int right = cols - layer - 1;

            // top row
            for (int j = left; j <= right; j++) {
                vals.add(grid[top][j]);
            }

            // right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                vals.add(grid[i][right]);
            }

            // bottom row
            for (int j = right; j >= left; j--) {
                vals.add(grid[bottom][j]);
            }

            // left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                vals.add(grid[i][left]);
            }

            int size = vals.size();
            int rotate = k % size;

            List<Integer> rotated = new ArrayList<>(size);

            // counter-clockwise rotation
            for (int i = 0; i < size; i++) {
                rotated.add(vals.get((i + rotate) % size));
            }

            int idx = 0;

            // put back top row
            for (int j = left; j <= right; j++) {
                grid[top][j] = rotated.get(idx++);
            }

            // put back right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                grid[i][right] = rotated.get(idx++);
            }

            // put back bottom row
            for (int j = right; j >= left; j--) {
                grid[bottom][j] = rotated.get(idx++);
            }

            // put back left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                grid[i][left] = rotated.get(idx++);
            }
        }

        return grid;
    }
}