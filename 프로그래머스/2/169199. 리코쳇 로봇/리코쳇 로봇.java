import java.util.*;

class Solution {
    
    // 상하좌우 이동
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(String[] board) {
        
        int n = board.length;
        int m = board[0].length();
        
        // 방문 체크
        boolean[][] visited = new boolean[n][m];
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        // 시작 위치 R 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    queue.offer(new int[]{i, j, 0}); // x, y, 이동 횟수
                    visited[i][j] = true;
                }
            }
        }
        
        // BFS 시작
        while (!queue.isEmpty()) {
            
            int[] cur = queue.poll();
            
            int x = cur[0];
            int y = cur[1];
            int count = cur[2];
            
            // 목표 도착
            if (board[x].charAt(y) == 'G') {
                return count;
            }
            
            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                
                int nx = x;
                int ny = y;
                
                // 끝까지 미끄러지기
                while (true) {
                    
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];
                    
                    // 범위 밖 or 장애물
                    if (tx < 0 || ty < 0 || tx >= n || ty >= m
                        || board[tx].charAt(ty) == 'D') {
                        break;
                    }
                    
                    nx = tx;
                    ny = ty;
                }
                
                // 이미 방문한 위치면 스킵
                if (visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny, count + 1});
            }
        }
        
        // 도달 불가능
        return -1;
    }
}