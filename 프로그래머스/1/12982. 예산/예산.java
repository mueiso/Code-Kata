import java.util.Arrays;

class Solution {
    
    public int solution(int[] d, int budget) {
        
        // 금액이 작은 순서로 정렬
        Arrays.sort(d);
        
        int count = 0;
        
        for (int money : d) {
            // 예산이 부족하면 종료
            if (budget < money) {
                break;
            }
            
            // 예산 차감
            budget -= money;
            
            // 지원한 부서 수 증가
            count++;
        }
        
        return count;
    }
}