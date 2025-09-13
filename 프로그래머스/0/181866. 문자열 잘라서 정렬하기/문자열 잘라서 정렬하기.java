import java.util.*;

class Solution {
    
    public String[] solution(String myString) {
        
        /*
         * myString을 "x" 기준으로 split → 문자열 배열 생성
         * Arrays.stream()으로 배열을 스트림으로 변환
         */
        return Arrays.stream(myString.split("x"))
                // 빈 문자열("")은 제외 (필터링)
                .filter(s -> !s.isEmpty())
                // 사전순 정렬
                .sorted()
                // 정렬된 스트림을 String 배열로 변환
                .toArray(String[]::new);
    }
}
