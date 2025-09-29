class Solution {
    
    public int solution(int n) {
        
        // x를 2부터 n-1까지 순회
        for (int x = 2; x < n; x++) {
            // n을 x로 나눴을 때 나머지가 1이면 그 x를 반환
            if (n % x == 1) {
                return x;
            }
        }
        
        // 반복문에서 못 찾으면 n-1이 항상 답 (예: n=2일 때)
        return n - 1;
    }
}
