import java.util.*;

class Solution {
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
        int answer = 0;

        // 1. 정렬
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return b[0] - a[0]; // 기본키 내림차순
            }
            
            return a[col - 1] - b[col - 1]; // col 기준 오름차순
        });

        // 2. row_begin ~ row_end까지 처리
        for (int i = row_begin; i <= row_end; i++) {
            int sum = 0;

            // i번째 행 (index는 i-1)
            for (int val : data[i - 1]) {
                sum += val % i;
            }

            // 3. XOR 누적
            answer ^= sum;
        }

        return answer;
    }
}