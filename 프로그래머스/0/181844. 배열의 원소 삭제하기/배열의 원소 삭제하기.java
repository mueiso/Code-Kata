import java.util.*;
import java.util.stream.*;

class Solution {
    
    public int[] solution(int[] arr, int[] delete_list) {
    
        
        // 삭제해야 할 숫자들을 HashSet에 저장 (탐색 시간 단축)
        Set<Integer> deleteSet = Arrays.stream(delete_list)
                                       .boxed()
                                       .collect(Collectors.toSet());

        // arr에서 deleteSet에 없는 값만 남김
        return Arrays.stream(arr)
                     .filter(num -> !deleteSet.contains(num))
                     .toArray();
    }
}