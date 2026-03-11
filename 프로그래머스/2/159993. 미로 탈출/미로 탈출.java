import java.util.*;

class Solution {

    // 상하좌우 이동 방향
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(String[] maps) {

        int n = maps.length;      // 세로 길이
        int m = maps[0].length(); // 가로 길이

        int sx = 0, sy = 0; // 시작점 S
        int lx = 0, ly = 0; // 레버 L

        // 맵을 탐색하면서 S와 L 위치 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (maps[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }

                if (maps[i].charAt(j) == 'L') {
                    lx = i;
                    ly = j;
                }
            }
        }

        // 시작 → 레버 최소 거리
        int startToLever = bfs(maps, sx, sy, 'L');

        // 레버까지 못 가면 탈출 불가
        if (startToLever == -1) {
            return -1;
        }

        // 레버 → 출구 최소 거리
        int leverToExit = bfs(maps, lx, ly, 'E');

        // 출구까지 못 가면 탈출 불가
        if (leverToExit == -1) {
            return -1;
        }

        // 총 이동 시간
        return startToLever + leverToExit;
    }

    // BFS로 시작점 → 목표 문자까지의 최단 거리 찾기
    private int bfs(String[] maps, int startX, int startY, char target) {

        int n = maps.length;
        int m = maps[0].length();

        boolean[][] visited = new boolean[n][m]; // 방문 체크

        Queue<int[]> queue = new LinkedList<>();

        // 시작 좌표와 거리 0 저장
        queue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            // 목표 위치에 도달하면 거리 반환
            if (maps[x].charAt(y) == target) {
                return dist;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                // 맵 범위 안 + 방문 안함 + 벽이 아님
                if (nx >= 0
                    && ny >= 0
                    && nx < n
                    && ny < m
                    && !visited[nx][ny]
                    && maps[nx].charAt(ny) != 'X') {

                    visited[nx][ny] = true;

                    // 다음 좌표와 거리 +1
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }

        // 목표 지점에 도달하지 못함
        return -1;
    }
}