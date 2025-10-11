/* [문제풀이]
 * Arrays.sort(num_list)
   → 배열을 오름차순으로 정렬

 * Arrays.copyOfRange(num_list, 5, num_list.length)
   → 정렬된 배열에서 앞의 5개(가장 작은 다섯 개) 를 제외하고, 나머지만 복사

 * 새로운 배열을 반환
 */

import java.util.*;

class Solution {
    
    public int[] solution(int[] num_list) {
        
        // 1. 배열 오름차순 정렬
        Arrays.sort(num_list);
        
        // 2. 5개를 제외한 나머지 부분만 복사
        //    예: 길이 10이면 index 5~9만 복사
        return Arrays.copyOfRange(num_list, 5, num_list.length);
    }
}