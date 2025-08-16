import java.util.*;

class Solution {
    
    public String solution(String my_string, int[] indices) {
        
        // 제거할 인덱스를 Set에 저장 (빠른 검색 가능)
        Set<Integer> removeSet = new HashSet<>();
        
        for (int idx : indices) {
            removeSet.add(idx);
        }
        
        // 문자열 빌더로 결과 생성
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < my_string.length(); i++) {
            if (!removeSet.contains(i)) { // Set에 없으면 추가
                sb.append(my_string.charAt(i));
            }
        }
        
        return sb.toString();
    }
}
