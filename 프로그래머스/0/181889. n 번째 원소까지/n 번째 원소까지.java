import java.util.Arrays;

class Solution {
    
    public int[] solution(int[] num_list, int n) {
        
        // 0 이상 n 미만 구간 복사
        return Arrays.copyOfRange(num_list, 0, n);
    }
}
