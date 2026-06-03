class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        
        int mod = 1000000007;
        
        // dp[세로][가로] -> 문제의 n(행), m(열)에 맞춤
        int[][] dp = new int[n + 1][m + 1];

        // 물웅덩이 표시 (-1)
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        dp[1][1] = 1; // 시작점 (집)

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 시작점이거나 물웅덩이인 경우 건너뜀
                if (dp[i][j] == -1) {
                    dp[i][j] = 0; // 계산을 위해 0으로 변경
                    continue;
                }
                
                // 위쪽에서 오는 경로 더하기
                if (i > 1) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
                }
                // 왼쪽에서 오는 경로 더하기
                if (j > 1) {
                    dp[i][j] = (dp[i][j] + dp[i][j - 1]) % mod;
                }
            }
        }

        return dp[n][m];
    }
}