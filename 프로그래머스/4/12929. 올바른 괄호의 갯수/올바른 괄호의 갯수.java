class Solution {
    
    public int solution(int n) {
        
        int[] dp = new int[n + 1];
        
        // Base Case
        dp[0] = 1;
        dp[1] = 1;

        // DP 상태 전이
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[n];
    }
}