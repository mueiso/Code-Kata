class Solution {
    
    public String solution(String s, int n) {
        
        String answer = "";
        // 문자열을 문자 배열로 변환
        char[] ch = s.toCharArray();
        
        for (char c : ch) {
            
            // 공백(ASCII 32)은 그대로 유지
            if (c == 32) {
                answer += " ";
            } 
            else {
                // 대문자 범위 (A=65 ~ Z=90)
                if (c <= 90) {
                    c += n;              // n만큼 밀기
                    if (c > 90) c -= 26; // 'Z'를 넘어가면 'A'로 순환
                } 
                // 소문자 범위 (a=97 ~ z=122)
                else {
                    c += n;
                    if (c > 122) c -= 26; // 'z'를 넘어가면 'a'로 순환
                }
                answer += c;              // 변환된 문자 추가
            }
        }
        
        return answer;
    }
}