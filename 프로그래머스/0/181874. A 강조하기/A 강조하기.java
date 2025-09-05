class Solution {
    
    public String solution(String myString) {
        
        // 우선 전체를 소문자로 변환 (대문자를 소문자로 바꾸기 위해)
        String lower = myString.toLowerCase();
        
        // 소문자 "a"를 대문자 "A"로 변환
        String result = lower.replace('a', 'A');
        
        return result;
    }
}
