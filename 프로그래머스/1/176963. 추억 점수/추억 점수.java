import java.util.*;

class Solution {
    
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
                
        // 이름 -> 점수 매핑
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        
        // 사진 개수만큼 결과 배열 생성
        int[] answer = new int[photo.length];
        
        // 각 사진별 점수 계산
        for(int i = 0; i < photo.length; i++) {
            int sum = 0;
            
            for(String person : photo[i]) {
                sum += map.getOrDefault(person, 0);  // 없으면 0
            }
            
            answer[i] = sum;
        }
        
        return answer;
    }
}