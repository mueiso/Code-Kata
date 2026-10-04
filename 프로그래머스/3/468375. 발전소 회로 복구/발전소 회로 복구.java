import java.util.*;

class Solution {
    
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        
        int n = grid.length;
        int m = grid[0].length();
        int k = panels.length;

        // 1. 엘리베이터 위치 탐색
        int er = -1, ec = -1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r].charAt(c) == '@') {
                    er = r;
                    ec = c;
                    break;
                }
            }
        }

        // 2. 패널 위치 정제 (0-indexed 변환)
        int[][] pPos = new int[k][3]; // [floor, row, col]
        for (int i = 0; i < k; i++) {
            pPos[i][0] = panels[i][0] - 1;
            pPos[i][1] = panels[i][1] - 1;
            pPos[i][2] = panels[i][2] - 1;
        }

        // 3. 패널별 선행 제약조건 비트마스크 생성
        int[] prereq = new int[k];
        for (int[] seq : seqs) {
            int a = seq[0] - 1;
            int b = seq[1] - 1;
            prereq[b] |= (1 << a);
        }

        // 4. 엘리베이터 및 각 패널 위치에서 2D BFS 수행
        int[] elevatorD2D = bfs(grid, n, m, er, ec);

        int[][] panelD2D = new int[k][];
        for (int i = 0; i < k; i++) {
            panelD2D[i] = bfs(grid, n, m, pPos[i][1], pPos[i][2]);
        }

        // 5. 패널 간 최단 이동 거리 dist[i][j] 계산
        int[][] dist = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (pPos[i][0] == pPos[j][0]) {
                    // 같은 층: 2D 직접 이동
                    dist[i][j] = panelD2D[i][pPos[j][1] * m + pPos[j][2]];
                } else {
                    // 다른 층: 엘리베이터 경유
                    int d1 = elevatorD2D[pPos[i][1] * m + pPos[i][2]];
                    int d2 = elevatorD2D[pPos[j][1] * m + pPos[j][2]];
                    int floorDiff = Math.abs(pPos[i][0] - pPos[j][0]);
                    dist[i][j] = d1 + floorDiff + d2;
                }
            }
        }

        // 6. 비트마스크 DP
        int INF = 1_000_000_000;
        int[][] dp = new int[1 << k][k];
        for (int mask = 0; mask < (1 << k); mask++) {
            Arrays.fill(dp[mask], INF);
        }

        // 초기 상태: 선행 조건이 없는 패널 v를 첫 출발지(1번 패널 위치)에서 이동하여 활성화
        for (int v = 0; v < k; v++) {
            if (prereq[v] == 0) {
                dp[1 << v][v] = dist[0][v];
            }
        }

        // DP 상태 전이
        for (int mask = 1; mask < (1 << k); mask++) {
            for (int u = 0; u < k; u++) {
                if ((mask & (1 << u)) == 0 || dp[mask][u] == INF) continue;

                for (int v = 0; v < k; v++) {
                    // v번 패널이 아직 비활성화 상태이고, v의 선행 조건이 모두 충족되었을 때
                    if ((mask & (1 << v)) == 0 && (mask & prereq[v]) == prereq[v]) {
                        int nextMask = mask | (1 << v);
                        dp[nextMask][v] = Math.min(dp[nextMask][v], dp[mask][u] + dist[u][v]);
                    }
                }
            }
        }

        // 모든 패널을 활성화했을 때의 최솟값 탐색
        int fullMask = (1 << k) - 1;
        int answer = INF;
        for (int u = 0; u < k; u++) {
            answer = Math.min(answer, dp[fullMask][u]);
        }

        return answer;
    }

    /* 2D 격자상에서 (sr, sc)로부터 모든 칸까지의 최단 거리를 구하는 BFS */
    private int[] bfs(String[] grid, int n, int m, int sr, int sc) {
        
        int[] d = new int[n * m];
        Arrays.fill(d, 1_000_000_000);

        Queue<Integer> q = new ArrayDeque<>();
        d[sr * m + sc] = 0;
        q.offer(sr * m + sc);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int curr = q.poll();
            int r = curr / m;
            int c = curr % m;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr].charAt(nc) != '#') {
                    int nidx = nr * m + nc;
                    if (d[nidx] > d[curr] + 1) {
                        d[nidx] = d[curr] + 1;
                        q.offer(nidx);
                    }
                }
            }
        }

        return d;
    }
}