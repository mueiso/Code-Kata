class Solution {
    
    public int solution(int n, int count) {
        
        final long MOD = 1_000_000_007L;
        long[][] dp = new long[n + 1][n + 1];
        dp[1][1] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[i][j] == 0) continue;
                // 맨 앞에 넣기 → 보임
                dp[i + 1][j + 1] = (dp[i + 1][j + 1] + dp[i][j]) % MOD;
                // 나머지 2i개 틈에 넣기 → 가려짐
                dp[i + 1][j] = (dp[i + 1][j] + dp[i][j] * (2L * i)) % MOD;
            }
        }
        
        return (int) dp[n][count];
    }
}