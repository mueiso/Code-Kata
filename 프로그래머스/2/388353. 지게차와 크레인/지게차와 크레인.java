import java.util.*;

class Solution {
    
    public int solution(String[] storage, String[] requests) {
        
        int n = storage.length;
        int m = storage[0].length();
        
        // 상하좌우에 빈 공간(여백)을 둔 n+2 x m+2 크기의 맵 생성
        char[][] grid = new char[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(grid[i], '.'); // 빈 공간은 '.' 으로 초기화
        }
        
        // 맵의 중앙에 초기 컨테이너 상태 배치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        
        // 상하좌우 탐색용 배열
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (String req : requests) {
            char target = req.charAt(0);
            
            if (req.length() == 2) {
                // 크레인: 외부 연결 여부 상관없이 일치하는 모든 컨테이너 제거
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (grid[i][j] == target) {
                            grid[i][j] = '.';
                        }
                    }
                }
            } else {
                // 지게차: 외부와 연결된 컨테이너만 제거 (BFS 활용)
                boolean[][] visited = new boolean[n + 2][m + 2];
                Queue<int[]> queue = new LinkedList<>();
                List<int[]> toRemove = new ArrayList<>();
                
                // (0, 0)은 항상 창고 외부(빈 공간)임이 보장됨
                queue.offer(new int[]{0, 0});
                visited[0][0] = true;
                
                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    int r = curr[0];
                    int c = curr[1];
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        
                        // 맵 범위를 벗어나지 않고, 방문하지 않은 곳 탐색
                        if (nr >= 0 && nr < n + 2 && nc >= 0 && nc < m + 2 && !visited[nr][nc]) {
                            if (grid[nr][nc] == '.') {
                                // 빈 공간이면 큐에 추가하여 외부 공간을 계속 확장함
                                visited[nr][nc] = true;
                                queue.offer(new int[]{nr, nc});
                            } else if (grid[nr][nc] == target) {
                                // 타겟 컨테이너를 만나면 제거 목록에 추가 (동일 컨테이너 중복 방지를 위해 visited 처리)
                                visited[nr][nc] = true;
                                toRemove.add(new int[]{nr, nc});
                            }
                        }
                    }
                }
                
                // BFS 탐색이 끝난 후, 접근 가능했던 컨테이너들을 일괄 제거
                for (int[] pos : toRemove) {
                    grid[pos[0]][pos[1]] = '.';
                }
            }
        }
        
        // 모든 요청 처리 후 남아있는 컨테이너 개수 카운트
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i][j] != '.') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}