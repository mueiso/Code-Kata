import java.util.*;

class Solution {
    
     // 땅의 세로, 가로 길이
     int n;
     int m;
        
     // 각 칸이 어떤 석유 덩어리에 속하는지 기록할 배열
     int[][] group;
        
     // 상하좌우 이동 방향
     int[] dx = {1, -1, 0, 0};
     int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        
        n = land.length;
        m = land[0].length;
        
        // 각 칸의 석유 덩이리 번호를 저장
        group = new int[n][m];
        
        // 덩어리 번호 -> 덩어리 크기 저장
        Map<Integer, Integer> sizeMap = new HashMap<>();
        
        // 석유 덩어리 ID
        int id = 1;
        
        /*
         * 모든 석유 덩어리를 BFS로 탐색
         * 연결된 석유를 하나의 덩어리로 묶음
         * 덩어리 크기를 계산
         * 각 칸에 덩어리 번호 기록
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 석유이고 아직 방문하지 않은 칸이면 BFS 시작
                if (land[i][j] == 1 && group[i][j] == 0) {

                    int size = bfs(land, i, j, id);

                    // 덩어리 ID와 크기 저장
                    sizeMap.put(id, size);

                    id++;
                }
            }
        }

        int answer = 0;
        
        /*
         * 각 열마다 시추관을 설치한다고 가정하고 계산
         * 해당 열을 따라 내려가면서 만나는 석유 덩어리 ID 수집
         * 같은 덩어리는 한 번만 더해야 하므로 Set 사용
         */
        for (int col = 0; col < m; col++) {

            Set<Integer> set = new HashSet<>();

            int totalOil = 0;

            for (int row = 0; row < n; row++) {

                int groupId = group[row][col];

                // 석유가 있고 아직 더하지 않은 덩어리라면
                if (groupId > 0 && !set.contains(groupId)) {

                    set.add(groupId);

                    // 해당 덩어리의 크기를 더함
                    totalOil += sizeMap.get(groupId);
                }
            }

            // 최대 석유량 갱신
            answer = Math.max(answer, totalOil);
        }
        
        return answer;
    }
    
    /* [BFS를 이용한 하나의 석유 덩어리 탐색]
    
     * 시작 좌표부터 연결된 모든 석유를 탐색
     * 덩어리 크기를 계산
     * 각 칸에 덩어리 ID 기록
     */
    private int bfs(int[][] land, int x, int y, int id) {
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{x,y});
        
        // 시작 위치 덩어리 ID 기록
        group[x][y] = id;
        
        // 덩어리 크기
        int count = 1;
        
        while (!queue.isEmpty()) {
            
            int[] cur = queue.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            
            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {
                
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                
                // 범위 밖이면 스킵
                if (nx < 0
                   || ny < 0
                   || nx >= n
                   || ny >= m) {
                    
                    continue;
                }
                
                // 석유이고 아직 방문하지 않은 칸이면
                if (land[nx][ny] == 1 && group[nx][ny] == 0) {
                    
                    // 같은 덩어리로 표시
                    group[nx][ny] = id;
                    
                    count++;
                    
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        
        return count;
    }
}