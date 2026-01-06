class Solution {
    
    public long solution(int n) {
        
        final int MOD = 1234567;

        // n이 1 또는 2일 때 바로 처리
        if (n == 1) return 1;
        if (n == 2) return 2;

        long prev2 = 1; // dp[1]
        long prev1 = 2; // dp[2]
        long cur = 0;

        // dp[n] = dp[n-1] + dp[n-2]
        for (int i = 3; i <= n; i++) {
            cur = (prev1 + prev2) % MOD;
            prev2 = prev1;
            prev1 = cur;
        }

        return cur;
    }
}