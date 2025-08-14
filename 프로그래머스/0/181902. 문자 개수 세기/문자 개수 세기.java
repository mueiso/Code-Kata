class Solution {
    
    public int[] solution(String my_string) {

        /*
         * 알파벳 개수를 저장할 배열
         * 인덱스 0~25  → 'A' ~ 'Z'
         * 인덱스 26~51 → 'a' ~ 'z'
         */
        int[] answer = new int[52];
        
        // 문자열의 모든 문자를 순회
        for (int i = 0; i < my_string.length(); i++) {
            
            // 현재 위치의 문자 추출
            char c = my_string.charAt(i);
            
            /*
             * 문자가 소문자인 경우:
             * 'a'의 아스키 코드값을 빼면 0~25 범위가 나옴
             * 여기에 +26을 해서 인덱스를 26~51 범위로 맞춰줌
             */
            if (c >= 'a') {
                answer[c - 'a' + 26]++;
            } 
            /*
             * 문자가 대문자인 경우:
             * 'A'의 아스키 코드값을 빼면 0~25 범위가 나옴
             * 그대로 인덱스로 사용 (대문자 영역)
             */
            else {
                answer[c - 'A']++;
            }
        }
        
        // 대문자 26개, 소문자 26개의 개수가 담긴 배열 반환
        return answer;
    }
}
