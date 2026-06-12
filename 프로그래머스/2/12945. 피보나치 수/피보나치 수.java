class Solution {
    
    public int solution(int n) {
        
        int mod = 1234567;
        
        // n번째 피보나치 수까지 저장할 배열 선언
        int[] dp = new int[n + 1];
        
        // 초기값 설정
        dp[0] = 0;
        dp[1] = 1;
        
        // 2부터 n까지 차례대로 피보나치 수를 구함
        for (int i = 2; i <= n; i++) {
            // 오버플로우 방지를 위해 더할 때마다 바로 나머지 연산 수행
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }
        
        return dp[n];
    }
}