import java.util.*;

class Solution {
    
    public String[] solution(String myStr) {
        
        // "a", "b", "c" 모두를 구분자로 사용해야 하므로 정규식 사용
        String[] parts = myStr.split("[abc]");
        
        List<String> list = new ArrayList<>();
        
        // 빈 문자열 제거
        for (String s : parts) {
            if (!s.isEmpty()) {
                list.add(s);
            }
        }

        // 결과가 비었으면 ["EMPTY"] 반환
        if (list.isEmpty()) {
            return new String[] {"EMPTY"};
        }

        // 리스트를 배열로 변환해서 반환
        return list.toArray(new String[0]);
    }
}
