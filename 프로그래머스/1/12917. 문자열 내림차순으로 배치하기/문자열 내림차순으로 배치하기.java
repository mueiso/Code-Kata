import java.util.*;

class Solution {
    
    public String solution(String s) {
        
        // 문자열을 문자 배열로 변환
        Character[] charArray = new Character[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            charArray[i] = s.charAt(i);
        }
        
        // 내림차순 정렬 (대문자는 소문자보다 작게 취급)
        Arrays.sort(charArray, (a, b) -> b - a);
        
        // 정렬된 문자 배열을 문자열로 변환
        StringBuilder sb = new StringBuilder();
        
        for (char c : charArray) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}