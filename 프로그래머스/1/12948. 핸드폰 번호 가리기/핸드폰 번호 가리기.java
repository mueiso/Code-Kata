public class Solution {
    
    public String solution(String phone_number) {
        
        // 1. 전화번호의 앞부분(마지막 4자리 제외)을 추출
        String front = phone_number.substring(0, phone_number.length() - 4);
        // 2. 앞부분의 모든 문자를 '*'로 변환 ("."은 정규식에서 '모든 문자'를 의미)
        String hidden = front.replaceAll(".", "*");
        // 3. 마지막 4자리를 추출
        String back = phone_number.substring(phone_number.length() - 4);
        // 4. 앞부분(숨긴 부분) + 뒷부분(실제 번호 4자리)을 결합
        String answer = hidden + back;

        return answer;
    }
}