import java.util.*;
import java.util.stream.*;

class Solution {
    
    public int[] solution(int[] num_list) {
        
        return Arrays.stream(num_list)
                     .sorted()      // 오름차순 정렬
                     .limit(5)      // 앞에서 5개만 선택
                     .toArray();    // int[]로 변환
    }
}
