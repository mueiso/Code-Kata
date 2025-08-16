import java.util.*;

class Solution {
    
    public String solution(String my_string, int[] indices) {
        
        // StringBuilder는 문자열을 자주 수정(추가/삭제)할 때 성능이 좋음
        StringBuilder sb = new StringBuilder();
        
        // 삭제할 인덱스를 빠르게 찾기 위해 boolean 배열 생성
        boolean[] remove = new boolean[my_string.length()];
        
        // indices 배열에 있는 위치를 true로 표시
        for (int idx : indices) {
            remove[idx] = true;
        }
        
        // 문자열 순회하면서 삭제할 위치가 아니면 StringBuilder에 추가
        for (int i = 0; i < my_string.length(); i++) {
            if (!remove[i]) { // false라면 → 지우지 않는 문자
                sb.append(my_string.charAt(i));
            }
        }
        
        // 최종 문자열 반환
        return sb.toString();
    }
}
