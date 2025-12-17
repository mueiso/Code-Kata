import java.util.*;

class Solution {
    
    public int[] solution(String[] keymap, String[] targets) {

        // 각 문자에 대한 최소 입력 횟수 저장 (A~Z)
        int[] minPress = new int[26];
        Arrays.fill(minPress, Integer.MAX_VALUE);

        // 1) keymap 전처리: 문자별 최소 누름 횟수 계산
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int idx = c - 'A';
                // i는 0-based, 실제 누름 횟수는 i+1
                minPress[idx] = Math.min(minPress[idx], i + 1);
            }
        }

        // 2) targets 계산
        int[] answer = new int[targets.length];

        for (int t = 0; t < targets.length; t++) {
            String target = targets[t];
            int sum = 0;
            boolean possible = true;

            for (int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);
                int idx = c - 'A';

                // 해당 문자를 만들 수 없는 경우
                if (minPress[idx] == Integer.MAX_VALUE) {
                    possible = false;
                    break;
                }

                sum += minPress[idx];
            }

            answer[t] = possible ? sum : -1;
        }

        return answer;
    }
}