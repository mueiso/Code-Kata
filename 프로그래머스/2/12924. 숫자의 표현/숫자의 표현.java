class Solution {
    
    public int solution(int n) {
        
        int answer = 0;
        
        // 1부터 n까지 홀수(i += 2)만 순회하면서 n의 약수인지 확인합니다.
        for (int i = 1; i <= n; i += 2) {
            if (n % i == 0) {
                answer++;
            }
        }
        
        return answer;
    }
}