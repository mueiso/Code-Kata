class Solution {
    
    public int solution(int n) {
        
        // Base cases
        if (n == 1) return 1;
        if (n == 2) return 3;
        if (n == 3) return 10;
        
        long MOD = 1000000007;
        long[] dp = new long[n + 1];
        
        // 초기값 설정
        dp[0] = 1; // 아무것도 안 까는 경우의 수 1
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        
        // sum_dp[m] : dp 인덱스를 3으로 나눈 나머지가 m인 dp값들의 누적 합
        long[] sum_dp = new long[3];
        
        for (int i = 4; i <= n; i++) {
            // 이번 루프에서 dp[i-4]가 누적 합 배열에 새롭게 추가됨
            int idx = i - 4;
            sum_dp[idx % 3] = (sum_dp[idx % 3] + dp[idx]) % MOD;
            
            // k >= 4 인 경우의 수들의 누적 합 계산 (2, 2, 4 규칙 적용)
            // 현재 i를 기준으로 3으로 나눈 나머지에 따라 알맞은 계수(2 또는 4)를 곱해줌
            long acc = (4 * sum_dp[i % 3] % MOD 
                      + 2 * sum_dp[(i - 1) % 3] % MOD 
                      + 2 * sum_dp[(i - 2) % 3] % MOD) % MOD;
            
            // 최종 점화식 적용
            dp[i] = (dp[i - 1] * 1 % MOD 
                   + dp[i - 2] * 2 % MOD 
                   + dp[i - 3] * 5 % MOD 
                   + acc) % MOD;
        }
        
        return (int) dp[n];
    }
}