class Solution {
    
    static final long MOD = 1_000_000_007L;
    int N;

    public int solution(int[][] grid, int[] d, int k) {
        
        int n = grid.length, m = grid[0].length;
        N = n * m;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        // M = d를 한 바퀴 따라가는 전이 행렬 (단위행렬에서 시작)
        long[][] M = identity();
        for (int x : d) {
            long[][] next = new long[N][N];
            for (int i = 0; i < N; i++) {
                for (int u = 0; u < N; u++) {
                    if (M[i][u] == 0) continue;
                    int r = u / m, c = u % m;
                    for (int t = 0; t < 4; t++) {
                        int nr = r + dr[t], nc = c + dc[t];
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if (grid[nr][nc] - grid[r][c] != x) continue;
                        int v = nr * m + nc;
                        next[i][v] = (next[i][v] + M[i][u]) % MOD;
                    }
                }
            }
            M = next;
        }

        // M^k
        long[][] R = identity();
        long e = k;
        while (e > 0) {
            if ((e & 1) == 1) R = mul(R, M);
            M = mul(M, M);
            e >>= 1;
        }

        long sum = 0;
        for (long[] row : R)
            for (long v : row) sum = (sum + v) % MOD;
        return (int) sum;
    }

    long[][] identity() {
        long[][] I = new long[N][N];
        for (int i = 0; i < N; i++) I[i][i] = 1;
        return I;
    }

    long[][] mul(long[][] A, long[][] B) {
        long[][] C = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int t = 0; t < N; t++) {
                if (A[i][t] == 0) continue;
                long a = A[i][t];
                for (int j = 0; j < N; j++) {
                    C[i][j] = (C[i][j] + a * B[t][j]) % MOD;
                }
            }
        }
        return C;
    }
}