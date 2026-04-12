import java.util.ArrayList;
import java.util.List;

class Solution {
    
    int minTurns = Integer.MAX_VALUE;
    int n, m;
    int[][] map;
    
    // 상하좌우 이동을 위한 방향 배열
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    // 목적지 좌표
    int rdr, rdc, bdr, bdc;

    public int solution(int[][] maze) {
        
        n = maze.length;
        m = maze[0].length;
        map = maze;

        int rr = 0, rc = 0, br = 0, bc = 0;
        boolean[][] rVis = new boolean[n][m];
        boolean[][] bVis = new boolean[n][m];

        // 1. 시작점 및 목적지 좌표 초기화
        for (int i = 0; i < n; i++) {
            
            for (int j = 0; j < m; j++) {
                
                if (maze[i][j] == 1) { // 빨간 수레 시작점
                    rr = i; rc = j;
                    rVis[i][j] = true;
                } else if (maze[i][j] == 2) { // 파란 수레 시작점
                    br = i; bc = j;
                    bVis[i][j] = true;
                } else if (maze[i][j] == 3) { // 빨간 수레 목적지
                    rdr = i; rdc = j;
                } else if (maze[i][j] == 4) { // 파란 수레 목적지
                    bdr = i; bdc = j;
                }
            }
        }

        // 2. DFS(백트래킹) 탐색 시작
        dfs(rr, rc, br, bc, rVis, bVis, 0);

        // 3. 목적지에 도달할 수 없는 경우 0 반환
        return minTurns == Integer.MAX_VALUE ? 0 : minTurns;
    }

    private void dfs(int rr, int rc, int br, int bc, boolean[][] rVis, boolean[][] bVis, int turns) {
        
        boolean rDone = (rr == rdr && rc == rdc);
        boolean bDone = (br == bdr && bc == bdc);

        // 두 수레 모두 목적지에 도착한 경우
        if (rDone && bDone) {
            minTurns = Math.min(minTurns, turns);
            
            return;
        }

        // 가지치기: 현재 턴이 최소 턴 이상이면 탐색 중지
        if (turns >= minTurns)
            
            return;

        // 빨간 수레가 이동 가능한 다음 좌표들
        List<int[]> rMoves = new ArrayList<>();
        if (rDone) {
            rMoves.add(new int[]{rr, rc}); // 도착했다면 고정
        } else {
            for (int i = 0; i < 4; i++) {
                
                int nr = rr + dr[i];
                int nc = rc + dc[i];
                
                if (isValid(nr, nc) && !rVis[nr][nc]) {
                    rMoves.add(new int[]{nr, nc});
                }
            }
        }

        // 파란 수레가 이동 가능한 다음 좌표들
        List<int[]> bMoves = new ArrayList<>();
        if (bDone) {
            bMoves.add(new int[]{br, bc}); // 도착했다면 고정
        } else {
            for (int i = 0; i < 4; i++) {
                
                int nr = br + dr[i];
                int nc = bc + dc[i];
                
                if (isValid(nr, nc) && !bVis[nr][nc]) {
                    bMoves.add(new int[]{nr, nc});
                }
            }
        }

        // 두 수레의 다음 이동 경우의 수를 모두 조합하여 탐색
        for (int[] rm : rMoves) {
            
            for (int[] bm : bMoves) {
                
                // [조건 1] 두 수레가 같은 칸으로 이동할 수 없음
                if (rm[0] == bm[0] && rm[1] == bm[1])
                    
                    continue;
                
                // [조건 2] 두 수레가 서로 자리를 바꿀 수 없음
                if (rm[0] == br && rm[1] == bc && bm[0] == rr && bm[1] == rc)
                    
                    continue;

                // 이동 처리 (방문 체크)
                if (!rDone) rVis[rm[0]][rm[1]] = true;
                if (!bDone) bVis[bm[0]][bm[1]] = true;

                // 다음 턴으로 진행
                dfs(rm[0], rm[1], bm[0], bm[1], rVis, bVis, turns + 1);

                // 백트래킹 (방문 해제)
                if (!rDone) rVis[rm[0]][rm[1]] = false;
                if (!bDone) bVis[bm[0]][bm[1]] = false;
            }
        }
    }

    /* 격자 범위 내에 있고 벽(5)이 아닌지 확인하는 메서드 */
    private boolean isValid(int r, int c) {
        
        return r >= 0 && r < n && c >= 0 && c < m && map[r][c] != 5;
    }
}