import java.util.*;

class Solution {
    
    public int[] solution(int m, int n, int s, int[][] time_map) {
        
        final long INF = Long.MAX_VALUE;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        long[][] cur = new long[m][n];
        for (long[] row : cur) Arrays.fill(row, INF);
        cur[0][0] = 0;

        int moveDistance = 0;
        long sumOfTalkTime = 0;

        for (int d = 1; d <= m * n; d++) {
            long[][] next = new long[m][n];
            for (long[] row : next) Arrays.fill(row, INF);

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (cur[r][c] == INF) continue;
                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k], nc = c + dc[k];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                        if (time_map[nr][nc] == -1) continue;
                        long t = cur[r][c] + time_map[nr][nc];
                        if (t > s) continue;               // 제한 초과는 버림
                        if (t < next[nr][nc]) next[nr][nc] = t;
                    }
                }
            }

            if (next[m - 1][n - 1] != INF) {   // 처음 도착한 거리 = 최소 이동 거리
                moveDistance = d;
                sumOfTalkTime = next[m - 1][n - 1];
                break;
            }
            cur = next;
        }

        int[] answer = new int[2];
        answer[0] = moveDistance;
        answer[1] = (int) sumOfTalkTime;
        
        return answer;
    }
}