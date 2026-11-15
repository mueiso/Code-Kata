class Solution {
    
    // 방향: 0=위, 1=아래, 2=왼쪽, 3=오른쪽
    static final int[] DI = {-1, 1, 0, 0};
    static final int[] DJ = {0, 0, -1, 1};
    static final int[] OPP = {1, 0, 3, 2};

    // 대각선 g인 칸에서 삼각형 t가 닿는 두 방향
    static int[] dirs(int g, int t) {
        if (g == 1) return t == 0 ? new int[]{0, 2} : new int[]{1, 3};   // '/'
        else        return t == 0 ? new int[]{0, 3} : new int[]{1, 2};   // '\'
    }

    // 대각선 g인 칸에서 방향 d의 변에 닿는 삼각형 번호
    static int triOf(int g, int d) {
        if (g == 1) return (d == 0 || d == 2) ? 0 : 1;
        else        return (d == 0 || d == 3) ? 0 : 1;
    }

    public int solution(int[][] grid) {
        
        int N = grid.length, M = grid[0].length;
        int T = 2 * N * M;

        // 삼각형 x = 칸번호*2 + t, 이웃 두 개 (없으면 -1)
        int[] nb0 = new int[T], nb1 = new int[T];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int t = 0; t < 2; t++) {
                    int x = (i * M + j) * 2 + t;
                    int[] ds = dirs(grid[i][j], t);
                    for (int k = 0; k < 2; k++) {
                        int d = ds[k];
                        int ni = i + DI[d], nj = j + DJ[d];
                        int y = -1;
                        if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
                            y = (ni * M + nj) * 2 + triOf(grid[ni][nj], OPP[d]);
                        }
                        if (k == 0) nb0[x] = y; else nb1[x] = y;
                    }
                }
            }
        }

        boolean[] visited = new boolean[T];
        int[] seq = new int[T];
        int[] last = new int[N * M];      // 칸이 마지막으로 나온 위치
        int[] stamp = new int[N * M];     // 어느 덩어리 탐색에서 기록된 값인지
        int[] stampId = {0};
        int best = 0;

        // 1) 경로: 끝점(이웃이 하나 이하)에서 시작
        for (int x = 0; x < T; x++) {
            if (!visited[x] && (nb0[x] == -1 || nb1[x] == -1)) {
                int len = walk(x, nb0, nb1, visited, seq);
                best = Math.max(best, window(seq, len, false, last, stamp, stampId));
            }
        }
        // 2) 남은 것은 모두 사이클
        for (int x = 0; x < T; x++) {
            if (!visited[x]) {
                int len = walk(x, nb0, nb1, visited, seq);
                best = Math.max(best, window(seq, len, true, last, stamp, stampId));
            }
        }
        return best;
    }

    /* x에서 시작해 따라가며 칸 번호를 seq에 기록, 길이 반환 */
    private int walk(int start, int[] nb0, int[] nb1, boolean[] visited, int[] seq) {
        
        int len = 0, prev = -1, x = start;
        while (x != -1 && !visited[x]) {
            visited[x] = true;
            seq[len++] = x / 2;
            int next = (nb0[x] == prev) ? nb1[x] : nb0[x];
            prev = x;
            x = next;
        }
        
        return len;
    }

    /* 같은 칸이 두 번 나오지 않는 가장 긴 연속 구간 (사이클이면 원형) */
    private int window(int[] seq, int len, boolean cycle, int[] last, int[] stamp, int[] stampId) {
        
        int id = ++stampId[0];
        int total = cycle ? 2 * len : len;
        int l = 0, res = 0;
        for (int i = 0; i < total; i++) {
            int c = seq[i % len];
            if (stamp[c] == id && last[c] >= l) l = last[c] + 1;
            stamp[c] = id;
            last[c] = i;
            res = Math.max(res, Math.min(i - l + 1, len));
        }
        
        return res;
    }
}