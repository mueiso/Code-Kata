import java.util.*;

class Solution {
    
    public int[] solution(int[] num_list, int n) {
        
        int[] answer = new int[num_list.length];
        int idx = 0;

        // n번째 이후 원소 먼저 넣기
        for (int i = n; i < num_list.length; i++) {
            answer[idx++] = num_list[i];
        }

        // 그다음 n번째까지의 원소 넣기
        for (int i = 0; i < n; i++) {
            answer[idx++] = num_list[i];
        }

        return answer;
    }
}
