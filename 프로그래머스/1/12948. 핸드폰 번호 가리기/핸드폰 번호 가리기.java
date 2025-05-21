public class Solution {
    public String solution(String phone_number) {
        
        // 전화번호의 마지막 4자리를 제외한 나머지 부분을 '*'로 대체
        String answer = phone_number.substring(0, phone_number.length() - 4).replaceAll(".", "*") 
                        + phone_number.substring(phone_number.length() - 4);
        
        return answer;
    }
}