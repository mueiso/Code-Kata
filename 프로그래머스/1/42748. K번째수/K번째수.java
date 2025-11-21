import java.util.*;

class Solution {
    
    public int[] solution(int[] array, int[][] commands) {
        
        // commands의 개수만큼 결과 배열 생성
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            
            // commands[i] = [start, end, k]
            int start = commands[i][0] - 1; // i번째부터 → 인덱스는 0 기반이므로 -1
            int end   = commands[i][1];     // j번째까지 → copyOfRange의 end는 exclusive(미포함)
            int k     = commands[i][2] - 1; // k번째 숫자 → 0 기반 인덱스로 변환
            
            // array[start ~ end-1] 구간만 잘라서 새 배열 반환
            int[] sliced = Arrays.copyOfRange(array, start, end);
            
            // 잘라낸 배열 정렬
            Arrays.sort(sliced);
            
            // 정렬 후 k번째 숫자를 answer에 저장
            answer[i] = sliced[k];
        }
        
        return answer;
    }
}