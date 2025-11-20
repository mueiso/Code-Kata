import java.util.*;

class Solution {

    public String[] solution(String[] strings, int n) {
        
        // Comparator를 사용해 strings 배열을 정렬
        Arrays.sort(strings, (s1, s2) -> {

            // 두 문자열의 n번째 문자가 같다면
            if (s1.charAt(n) == s2.charAt(n)) {
                // 문자열 전체를 기준으로 사전순 비교
                return s1.compareTo(s2);
            } 
            else {
                // n번째 문자 기준으로 오름차순 비교
                return Character.compare(s1.charAt(n), s2.charAt(n));
            }
        });
        
        return strings;
    }
}