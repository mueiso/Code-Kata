class Solution {
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0;
        
        // 트럭이 남겨온 배달 가능 수량과 수거 가능 수량
        int deliver_amt = 0;
        int pickup_amt = 0;
        
        // 가장 멀리 있는 집(n-1)부터 물류창고 방향으로 거꾸로 탐색합니다.
        for (int i = n - 1; i >= 0; i--) {
            // 현재 집의 배달/수거 요구량을 차감합니다.
            deliver_amt -= deliveries[i];
            pickup_amt -= pickups[i];
            
            int count = 0; // 현재 집을 해결하기 위해 물류창고에서 출발해야 하는 횟수
            
            // 배달이나 수거 중 하나라도 부족하다면(마이너스라면) 트럭이 출발해야 합니다.
            while (deliver_amt < 0 || pickup_amt < 0) {
                deliver_amt += cap;
                pickup_amt += cap;
                count++;
            }
            
            // 트럭이 출발한 횟수가 있다면 왕복 거리를 누적 연산합니다.
            // i번째 인덱스 집의 거리는 (i + 1)입니다.
            if (count > 0) {
                answer += (long) (i + 1) * 2 * count;
            }
        }
        
        return answer;
    }
}