class Solution {
    
    public int solution(int[][] matrix_sizes) {
        
        int n = matrix_sizes.length;
        
        // dp[i][j] : i번째 행렬부터 j번째 행렬까지 곱하는 최소 연산 횟수
        int[][] dp = new int[n][n];

        // len은 곱할 행렬의 구간 길이 (2개부터 n개까지 늘려감)
        for (int len = 2; len <= n; len++) {
            // i는 구간의 시작 행렬 인덱스
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // j는 구간의 끝 행렬 인덱스
                dp[i][j] = Integer.MAX_VALUE; // 최소값을 구하기 위해 맥스값으로 초기화
                
                // k는 자르는 기준점 (i부터 j-1까지 이동)
                for (int k = i; k < j; k++) {
                    // 앞구간 비용 + 뒷구간 비용 + 두 결과 행렬을 곱하는 비용
                    int cost = dp[i][k] + dp[k + 1][j] 
                            + (matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1]);
                    
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        // 0번째 행렬부터 n-1번째 행렬까지 모두 곱한 최소 비용 반환
        return dp[0][n - 1];
    }
}