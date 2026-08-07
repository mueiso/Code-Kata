import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    
    public int[] solution(String msg) {
        
        Map<String, Integer> dict = new HashMap<>();
        
        // 1. A-Z 사전 초기화 (1 ~ 26)
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        List<Integer> result = new ArrayList<>();
        int nextIndex = 27; // 다음 등록할 사전의 색인 번호
        int i = 0;

        while (i < msg.length()) {
            String w = "";
            int j = i;

            // 2. 사전에 존재하는 가장 긴 문자열 w 찾기
            while (j < msg.length()) {
                String sub = msg.substring(i, j + 1);
                if (dict.containsKey(sub)) {
                    w = sub;
                    j++;
                } else {
                    break;
                }
            }

            // 3. w의 색인 번호 출력
            result.add(dict.get(w));

            // 4. 다음 글자 c가 남아있다면 w+c를 사전에 추가
            if (j < msg.length()) {
                String wc = msg.substring(i, j + 1); // w + c
                dict.put(wc, nextIndex++);
            }

            // 5. 처리한 w의 길이만큼 인덱스 이동
            i += w.length();
        }

        // List<Integer> -> int[] 변환
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}