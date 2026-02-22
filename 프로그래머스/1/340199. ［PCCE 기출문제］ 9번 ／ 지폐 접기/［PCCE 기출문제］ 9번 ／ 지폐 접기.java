class Solution {
    
    public int solution(int[] wallet, int[] bill) {
        
        int answer = 0;
        
        // 지페의 작은 값이 지갑의 작은 값보다 크거나
        // 지폐의 큰 값이 지갑의 큰 값보다 클 때
        while (
               Math.min(bill[0], bill[1]) > Math.min(wallet[0], wallet[1]) ||
               Math.max(bill[0], bill[1]) > Math.max(wallet[0], wallet[1])
              ) {
            
            // 더 긴 쪽을 반으로 접기
            if(bill[0] > bill[1]) {
                bill[0] /= 2;
            } else {
                bill[1] /= 2;
            }
            
            // 접은 횟수 증가
            answer++;
            
        }
        
        return answer;
    }
}