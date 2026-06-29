class Solution {
    
    public String solution(int n) {
        
        // 나머지가 0, 1, 2일 때 각각 매핑될 숫자를 배열로 선언합니다.
        // 인덱스: 0 -> "4", 1 -> "1", 2 -> "2"
        String[] digits = {"4", "1", "2"};
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            // 1. 현재 n을 3으로 나눈 나머지에 대응하는 숫자를 추가합니다.
            sb.append(digits[n % 3]);
            
            // 2. 124 나라의 자릿수 규칙에 맞춰 n을 갱신합니다.
            // 나머지가 0일 때 1을 빼주어야 하는 규칙이 이 수식 하나로 자동 처리됩니다.
            n = (n - 1) / 3;
        }

        // 일의 자리부터 거꾸로 추가되었으므로 뒤집어서 반환합니다.
        return sb.reverse().toString();
    }
}