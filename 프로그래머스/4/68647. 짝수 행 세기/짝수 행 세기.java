class Solution {
    
    static final long MOD = 10_000_019L;

    public int solution(int[][] a) {
        
        int R = a.length, C = a[0].length;

        // 이항계수 C(n, r), n ≤ 300
        long[][] comb = new long[R + 1][R + 1];
        for (int i = 0; i <= R; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        // 각 열의 1의 개수
        int[] ones = new int[C];
        for (int[] row : a)
            for (int j = 0; j < C; j++) ones[j] += row[j];

        long[] dp = new long[R + 1];
        dp[0] = 1;

        for (int col = 0; col < C; col++) {
            int c = ones[col];
            long[] next = new long[R + 1];
            for (int k = 0; k <= R; k++) {
                if (dp[k] == 0) continue;
                // j: 홀수 행에 넣는 1의 개수
                for (int j = 0; j <= Math.min(k, c); j++) {
                    int evenPart = c - j;              // 짝수 행에 넣는 1의 개수
                    if (evenPart > R - k) continue;
                    int nk = k - j + evenPart;
                    long ways = comb[k][j] * comb[R - k][evenPart] % MOD;
                    next[nk] = (next[nk] + dp[k] * ways) % MOD;
                }
            }
            dp = next;
        }
        
        return (int) dp[0];
    }
}