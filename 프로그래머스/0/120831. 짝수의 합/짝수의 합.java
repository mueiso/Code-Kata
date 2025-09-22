public class Solution {
    
    public int solution(int n) {
        
        int sum = 0;  // 합을 저장할 변수
        
        // 2부터 n까지 반복하면서 짝수만 더함
        for (int i = 2; i <= n; i += 2) {
            sum += i;
        }
        
        return sum;  // 최종 합 반환
    }
}
