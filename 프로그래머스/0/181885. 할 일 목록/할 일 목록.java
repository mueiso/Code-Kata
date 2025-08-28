import java.util.*;

class Solution {
    
    public String[] solution(String[] todo_list, boolean[] finished) {
        
        // 아직 마치지 못한 일을 담을 리스트
        List<String> notFinished = new ArrayList<>();
        
        // todo_list와 finished를 같은 인덱스로 순회
        for (int i = 0; i < todo_list.length; i++) {
            
            // 완료되지 않은 경우
            if (!finished[i]) {
                notFinished.add(todo_list[i]);
            }
        }
        
        // List -> 배열로 변환
        String[] answer = notFinished.toArray(new String[0]);
        
        return answer;
    }
}
