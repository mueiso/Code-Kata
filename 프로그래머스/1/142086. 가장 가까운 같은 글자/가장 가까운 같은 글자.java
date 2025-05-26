import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Map<Character, Integer> lastSeen = new HashMap<>(); // 문자가 마지막으로 등장한 위치를 저장

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (lastSeen.containsKey(ch)) {
                answer[i] = i - lastSeen.get(ch); // 현재 위치 - 마지막 위치
            } else {
                answer[i] = -1; // 처음 나온 문자
            }
            
            lastSeen.put(ch, i); // 현재 위치로 갱신
        }

        return answer;
    }
}