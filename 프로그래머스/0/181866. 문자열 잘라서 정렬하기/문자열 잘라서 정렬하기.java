import java.util.*;

class Solution {
    public String[] solution(String myString) {
        // 1. "x"를 기준으로 split
        String[] parts = myString.split("x");
        
        // 2. 빈 문자열 제거
        List<String> list = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                list.add(part);
            }
        }
        
        // 3. 리스트를 배열로 변환 후 정렬
        String[] answer = list.toArray(new String[0]);
        Arrays.sort(answer);
        
        return answer;
    }
}
