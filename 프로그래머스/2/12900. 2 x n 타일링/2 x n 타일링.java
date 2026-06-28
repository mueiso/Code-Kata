class Solution {
    
    public int solution(int n) {
        
        // n이 1이거나 2일 때는 직관적으로 바로 반환합니다.
        if (n == 1) return 1;
        if (n == 2) return 2;

        int MOD = 1000000007;
        
        // n원까지의 경우의 수를 담을 DP 테이블 선언
        int[] dp = new int[n + 1];
        
        // 초기 기저 조건 설정
        dp[1] = 1;
        dp[2] = 2;

        // 3부터 n까지 차례대로 더해가며 테이블을 채웁니다.
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return dp[n];
    }
}