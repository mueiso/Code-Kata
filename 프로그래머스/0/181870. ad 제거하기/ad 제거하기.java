import java.util.*;

class Solution {
    
    public String[] solution(String[] strArr) {
        
        // 결과를 담을 리스트
        List<String> list = new ArrayList<>();
        
        // 주어진 문자열 배열 순회
        for (String s : strArr) {
            // "ad"가 포함되지 않은 문자열만 결과 리스트에 추가
            if (!s.contains("ad")) {
                list.add(s);
            }
        }
        
        // 리스트를 배열로 변환하여 반환
        return list.toArray(new String[0]);
    }
}
