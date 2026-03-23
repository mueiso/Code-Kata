class Solution {
    
    public int solution(int storey) {
        
        int answer = 0;

        // 각 자릿수를 하나씩 처리
        while (storey > 0) {
            
            int digit = storey % 10;           // 현재 자리
            int next = (storey / 10) % 10;     // 다음 자리 (5 처리용)

            if (digit < 5) {
                
                // 내리는 게 더 적은 비용
                answer += digit;
                
            } else if (digit > 5) {
                
                // 올리는 게 더 적은 비용 → carry 발생
                answer += (10 - digit);
                storey += 10;
                
            } else { // digit == 5
                
                // 다음 자리 보고 결정
                answer += 5;
                
                if (next >= 5) {
                    storey += 10; // 올림 선택
                }
            }

            storey /= 10; // 다음 자리로 이동
        }

        return answer;
    }
}