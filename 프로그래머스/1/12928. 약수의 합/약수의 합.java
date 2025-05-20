class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i = 1; i <= n; i++) {
			answer += (n % i) == 0 ? i : 0;  // n이 i로 나누어떨어지면 i를 answer에 더한다
		}
        return answer;  // 약수들의 합 반환
    }
}