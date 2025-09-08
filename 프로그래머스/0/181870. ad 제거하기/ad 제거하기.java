import java.util.*;

class Solution {
    
    public String[] solution(String[] strArr) {
        
        return Arrays.stream(strArr)         // 배열 → 스트림 변환
                     .filter(s -> !s.contains("ad")) // "ad"가 포함되지 않은 문자열만 통과
                     .toArray(String[]::new); // 최종 결과를 배열로 변환
    }
}
