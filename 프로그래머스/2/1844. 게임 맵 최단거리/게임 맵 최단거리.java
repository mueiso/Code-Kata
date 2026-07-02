import java.util.*;

class Solution {
    
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;

        // 동, 서, 남, 북 이동을 위한 방향 벡터
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 방문 여부를 기록할 배열
        boolean[][] visited = new boolean[n][m];
        
        // BFS를 위한 큐 선언 (int[] 배열로 {행, 열, 이동거리}를 저장)
        Queue<int[]> queue = new LinkedList<>();

        // 시작점 (0, 0)에서 1칸 이동한 상태로 출발
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            // 우측 하단 목적지(상대 팀 진영)에 도착했다면 현재까지의 거리를 즉시 반환
            if (x == n - 1 && y == m - 1) {
                return dist;
            }

            // 사방 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 1. 맵의 범위를 벗어나지 않는지 체크
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 2. 갈 수 있는 길(1)이고, 아직 방문하지 않은 곳인지 체크
                    if (maps[nx][ny] == 1 && !visited[nx][ny]) {
                        // 큐에 넣기 직전에 방문 표시를 해야 중복 방문을 완벽히 방지합니다.
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }

        // 큐가 빌 때까지 목적지에 도달하지 못했다면 갈 수 없는 경로입니다.
        return -1;
    }
}