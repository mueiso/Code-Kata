import java.util.*;

class Solution {
    
    // 조합을 저장할 맵 (조합 문자열 : 빈도수)
    static Map<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        
        List<String> answerList = new ArrayList<>();

        // 1. 각 주문을 정렬 (가나다순으로 합치기 위함)
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // 2. 각 코스 요리 개수별로 탐색
        for (int len : course) {
            map = new HashMap<>();
            int maxVisit = 0;

            for (String order : orders) {
                if (order.length() >= len) {
                    combination(order, "", 0, len);
                }
            }

            // 3. 가장 많이 주문된 조합 찾기 (최소 2회 이상)
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                maxVisit = Math.max(maxVisit, entry.getValue());
            }

            if (maxVisit >= 2) {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == maxVisit) {
                        answerList.add(entry.getKey());
                    }
                }
            }
        }

        // 4. 최종 리스트 정렬 후 배열 변환
        Collections.sort(answerList);
        
        return answerList.toArray(new String[0]);
    }

    /* 조합(Combination)을 구하는 재귀 함수 */
    private void combination(String order, String res, int index, int targetLen) {
        
        if (res.length() == targetLen) {
            map.put(res, map.getOrDefault(res, 0) + 1);
            
            return;
        }

        for (int i = index; i < order.length(); i++) {
            combination(order, res + order.charAt(i), i + 1, targetLen);
        }
    }
}