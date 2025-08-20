import java.util.*;

class Solution {
    
    public int[] solution(int[] arr, int[][] intervals) {
        
        // 첫 번째 구간 [a1, b1]
        int[] first = Arrays.copyOfRange(arr, intervals[0][0], intervals[0][1] + 1);
        
        // 두 번째 구간 [a2, b2]
        int[] second = Arrays.copyOfRange(arr, intervals[1][0], intervals[1][1] + 1);

        // 두 배열을 합치기 위해 새로운 배열 생성
        int[] answer = new int[first.length + second.length];

        // 첫 번째 배열 복사
        System.arraycopy(first, 0, answer, 0, first.length);
        
        // 두 번째 배열 복사 (첫 번째 배열 뒤에 붙임)
        System.arraycopy(second, 0, answer, first.length, second.length);

        return answer;
    }
}
