import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int[][] map = new int[102][102];

        // 1. 모든 좌표를 2배 스케일링하여 지도에 그리기
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    // 직사각형 순수 내부인 경우 2로 설정
                    if (x > x1 && x < x2 && y > y1 && y < y2) {
                        map[x][y] = 2;
                    } 
                    // 내부(2)가 아닌 테두리인 경우만 1로 설정
                    else if (map[x][y] != 2) {
                        map[x][y] = 1;
                    }
                }
            }
        }

        // 2. BFS를 이용한 최단 거리 탐색
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[102][102];

        int startX = characterX * 2;
        int startY = characterY * 2;
        int targetX = itemX * 2;
        int targetY = itemY * 2;

        queue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            // 목적지 도달 시 2배 확장된 거리를 2로 나누어 반환
            if (x == targetX && y == targetY) {
                return dist / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx <= 100 && ny >= 0 && ny <= 100) {
                    // 테두리(1)이며 아직 방문하지 않은 칸만 이동
                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }

        return 0;
    }
}