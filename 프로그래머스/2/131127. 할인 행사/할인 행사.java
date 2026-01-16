import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public int solution(String[] want, int[] number, String[] discount) {
        
        int answer = 0;

        // 내가 원하는 상품과 수량을 Map에 저장
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // 10일씩 슬라이딩 윈도우로 확인
        for (int start = 0; start <= discount.length - 10; start++) {
            Map<String, Integer> discountMap = new HashMap<>();

            // 현재 10일간의 할인 품목 개수 세기
            for (int i = start; i < start + 10; i++) {
                discountMap.put(
                    discount[i],
                    discountMap.getOrDefault(discount[i], 0) + 1
                );
            }

            // 내가 원하는 상품 수량과 일치하는지 검사
            boolean possible = true;
            for (String key : wantMap.keySet()) {
                if (discountMap.getOrDefault(key, 0) != wantMap.get(key)) {
                    possible = false;
                    break;
                }
            }

            if (possible) answer++;
        }

        return answer;
    }
}