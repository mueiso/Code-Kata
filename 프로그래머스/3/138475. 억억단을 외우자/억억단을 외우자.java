class Solution {
    
    public int[] solution(int e, int[] starts) {
        
        // 1. 1부터 e까지의 약수 개수 구하기
        int[] divisors = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            // i의 배수들은 무조건 i를 약수로 가짐
            for (int j = i; j <= e; j += i) {
                divisors[j]++;
            }
        }
        
        // 2. dp[s] : s부터 e까지의 수 중 약수가 가장 많은 수 (동률 시 가장 작은 수)
        int[] dp = new int[e + 1];
        dp[e] = e;
        int maxDivisors = divisors[e];
        int maxNum = e;
        
        // e-1부터 1까지 역순으로 탐색하며 최댓값 기록
        for (int i = e - 1; i >= 1; i--) {
            // 약수 개수가 같거나 크면 갱신 (더 작은 수가 우선순위를 가지므로 >= 사용)
            if (divisors[i] >= maxDivisors) {
                maxDivisors = divisors[i];
                maxNum = i;
            }
            dp[i] = maxNum;
        }
        
        // 3. 쿼리(starts)에 대한 정답 배열 생성
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = dp[starts[i]];
        }
        
        return answer;
    }
}