class Solution {
    public int solution(int[][] dots) {
        int width = Math.abs(dots[0][0] - dots[1][0]);
        int height = Math.abs(dots[0][1] - dots[1][1]);

        for (int i = 0; i < dots.length; i++) {
            for (int j = i + 1; j < dots.length; j++) {
                int xDiff = Math.abs(dots[i][0] - dots[j][0]);
                int yDiff = Math.abs(dots[i][1] - dots[j][1]);

                if (xDiff != 0) width = Math.max(width, xDiff);
                if (yDiff != 0) height = Math.max(height, yDiff);
            }
        }

        return width * height;
    }
}
