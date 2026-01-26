import java.util.*;

class Solution {
    
    public int solution(int[] topping) {
        
        int answer = 0;

        // 오른쪽 조각의 토핑 개수 관리
        Map<Integer, Integer> rightMap = new HashMap<>();
        for (int t : topping) {
            rightMap.put(t, rightMap.getOrDefault(t, 0) + 1);
        }

        // 왼쪽 조각의 토핑 종류 관리
        Set<Integer> leftSet = new HashSet<>();

        // 마지막은 자를 수 없으므로 length - 1까지만 순회
        for (int i = 0; i < topping.length - 1; i++) {
            int cur = topping[i];

            // 왼쪽에 토핑 추가
            leftSet.add(cur);

            // 오른쪽에서 토핑 제거
            rightMap.put(cur, rightMap.get(cur) - 1);
            if (rightMap.get(cur) == 0) {
                rightMap.remove(cur);
            }

            // 토핑 종류 수가 같으면 공평
            if (leftSet.size() == rightMap.size()) {
                answer++;
            }
        }

        return answer;
    }
}