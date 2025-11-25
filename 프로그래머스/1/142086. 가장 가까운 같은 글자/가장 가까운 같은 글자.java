import java.util.*;

class Solution {
    
    public int[] solution(String s) {
        
        // 정답을 담을 배열 (각 인덱스별로 "가장 가까운 같은 문자까지의 거리" 저장)
        int[] answer = new int[s.length()];
        
        // 각 문자별로 "마지막으로 등장한 인덱스"를 저장할 Map
        Map<Character, Integer> lastSeen = new HashMap<>();

        // 문자열을 왼쪽부터 오른쪽으로 순회
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // 현재 위치의 문자
            
            // 이전에 이 문자가 등장한 적이 있다면
            if (lastSeen.containsKey(ch)) {
                // (현재 인덱스 i) - (바로 앞에서 나왔던 같은 문자 인덱스)
                answer[i] = i - lastSeen.get(ch);
            } else {
                // 처음 등장하는 문자라면 -1
                answer[i] = -1;
            }
            
            // 이 문자의 "마지막 등장 위치"를 현재 인덱스로 갱신
            lastSeen.put(ch, i);
        }

        return answer;
    }
}