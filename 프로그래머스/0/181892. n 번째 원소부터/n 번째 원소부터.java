import java.util.*;

class Solution {
    
    public int[] solution(int[] num_list, int n) {
        
        // n번째 원소부터 끝까지 반환해야 하므로, 배열 인덱스 기준으로는 n-1부터 시작해야 함
        int[] a= Arrays.copyOfRange(num_list, n-1, num_list.length);
        
        return a;
    }
}
