import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public int solution(String[][] clothes) {
        
        int answer = 1;

        // 의상 종류별 개수를 저장할 Map
        Map<String, Integer> map = new HashMap<>();

        // 각 의상의 "종류"별로 개수 세기
        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        /*
         * 조합 계산
         * 각 종류마다 (입지 않는 경우 1 + 해당 종류 개수)를 곱함
         */
        for (int count : map.values()) {
            answer *= (count + 1);
        }

        // 아무것도 안 입는 경우 1가지를 제외
        return answer - 1;
    }
}