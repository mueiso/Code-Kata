import java.util.*;

class Solution {
    
    public int[] solution(int[] num_list) {
        
        // 배열 정렬
        Arrays.sort(num_list);

        // 앞의 5개만 잘라내기
        return Arrays.copyOf(num_list, 5);
    }
}
