class Solution {
    
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        
        // dp[i][j][0]: 오른쪽 이동으로 (i, j)에 도달
        // dp[i][j][1]: 아래쪽 이동으로 (i, j)에 도달
        int[][][] dp = new int[m + 1][n + 1][2];

        // 출발점 초기화 (1-indexed 기준)
        dp[1][1][0] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;

                // 1. 왼쪽에서 오던 차 (오른쪽 진행)
                if (j > 1) {
                    int prevVal = cityMap[i - 1][j - 2];
                    if (prevVal == 0) {
                        dp[i][j][0] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                    } else if (prevVal == 2) {
                        dp[i][j][0] = dp[i][j - 1][0];
                    }
                }

                // 2. 위에서 오던 차 (아래쪽 진행)
                if (i > 1) {
                    int prevVal = cityMap[i - 2][j - 1];
                    if (prevVal == 0) {
                        dp[i][j][1] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                    } else if (prevVal == 2) {
                        dp[i][j][1] = dp[i - 1][j][1];
                    }
                }
            }
        }

        return (dp[m][n][0] + dp[m][n][1]) % MOD;
    }
}