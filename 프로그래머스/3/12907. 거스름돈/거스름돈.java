class Solution {
    
    public int solution(int n, int[] money) {
        
        // 1. n원까지의 경우의 수를 담을 DP 테이블 선언
        int[] dp = new int[n + 1];
        
        // 2. 0원을 만드는 방법은 아무것도 안 쓰는 '1가지'로 초기화
        dp[0] = 1;
        
        // 3. 동전 종류를 바깥쪽 루프에 둡니다 (조합을 구하기 위함)
        for (int coin : money) {
            // 현재 동전으로 감당할 수 있는 금액(coin)부터 n원까지 순회
            for (int i = coin; i <= n; i++) {
                // 기존 경우의 수에 (현재 금액 - 동전 가치)의 경우의 수를 더해줍니다.
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }
        
        // 4. 최종적으로 n원을 만드는 경우의 수 반환
        return dp[n];
    }
}