import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1; // 인덱스는 0부터 시작하므로 1 빼기
            int end = commands[i][1];       // Arrays.copyOfRange는 두 번째 인자가 exclusive
            int k = commands[i][2] - 1;     // 마찬가지로 0-based 인덱싱

            int[] sliced = Arrays.copyOfRange(array, start, end);
            Arrays.sort(sliced);
            answer[i] = sliced[k];
        }
        
        return answer;
    }
}
