import java.util.*;

class Solution {
    
    public String[] solution(String[] str_list) {
        
        // str_list를 순회하면서 l, r 중 먼저 나오는 위치를 찾는다
        for (int i = 0; i < str_list.length; i++) {
            
            if (str_list[i].equals("l")) {
                
                // "l"을 만나면 i 앞쪽 부분을 반환
                return Arrays.copyOfRange(str_list, 0, i);
                
            } else if (str_list[i].equals("r")) {
                
                // "r"을 만나면 i 뒤쪽 부분을 반환
                return Arrays.copyOfRange(str_list, i + 1, str_list.length);
            }
        }
        
        // "l"이나 "r"이 전혀 없는 경우 → 빈 배열 반환
        return new String[]{};
    }
}
