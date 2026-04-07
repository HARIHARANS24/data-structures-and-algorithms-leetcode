class Robot {
    int width, height, x, y, dir;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    String[] dirs = {"East", "North", "West", "South"};
    int perimeter;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0;
        this.perimeter = 2 * (width + height) - 4;
    }

    public void step(int num) {
        num %= perimeter;
        if (num == 0) num = perimeter;

        while (num-- > 0) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                dir = (dir + 1) % 4;
                num++;
            } else {
                x = nx;
                y = ny;
            }
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        return dirs[dir];
    }
}