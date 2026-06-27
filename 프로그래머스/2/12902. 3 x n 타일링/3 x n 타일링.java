class Solution {
    
    public int solution(int n) {
        
        // 1. n이 홀수이면 2x1 타일로 완벽히 채울 수 없으므로 0을 리턴합니다.
        if (n % 2 != 0) {
            return 0;
        }

        long MOD = 1000000007;
        // n원까지 채우기 위한 DP 테이블 선언 (인덱스 편의상 n+1 크기)
        long[] dp = new long[n + 1];

        // 2. 초기 기저 조건 설정
        dp[0] = 1; // 특수 모양 계산을 위한 가상의 기본값
        dp[2] = 3; // 3x2를 채우는 방법 3가지

        // 3. 4부터 n까지 짝수만 순회하며 DP 테이블을 채웁니다.
        for (int i = 4; i <= n; i += 2) {
            // f(i) = 4 * f(i-2) - f(i-4)
            // 나눗셈 연산 시 음수가 나오는 것을 방지하기 위해 MOD를 더해준 후 나머지 연산을 합니다.
            dp[i] = (4 * dp[i - 2] - dp[i - 4] + MOD) % MOD;
        }

        return (int) dp[n];
    }
}