import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    // 상하좌우 탐색을 위한 방향 배열
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};

    public int[] solution(int m, int n, int[][] picture) {
        
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[m][n];

        // 2차원 배열 전체를 순회
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 색이 칠해져 있고, 아직 방문하지 않은 새로운 영역의 시작점 발견
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++; // 새로운 영역 카운트 추가
                    
                    // BFS 시작
                    int currentAreaSize = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        currentAreaSize++; // 큐에서 꺼낼 때마다 크기 1 증가
                        
                        int cx = curr[0];
                        int cy = curr[1];
                        int color = picture[cx][cy];
                        
                        // 상하좌우 네 방향 확인
                        for (int d = 0; d < 4; d++) {
                            int nx = cx + dx[d];
                            int ny = cy + dy[d];
                            
                            // 맵 경계선 내부이면서
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                // 아직 방문 안 했고 시작점과 같은 색상이라면
                                if (!visited[nx][ny] && picture[nx][ny] == color) {
                                    visited[nx][ny] = true;
                                    queue.add(new int[]{nx, ny});
                                }
                            }
                        }
                    }
                    
                    // 탐색 완료 후 가장 큰 영역 크기 갱신
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, currentAreaSize);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
}