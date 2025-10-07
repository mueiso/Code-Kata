/* [문제풀이]
 * countByLen 해시맵을 만들어 문자열 길이별로 개수를 카운팅
   예: ["a", "bc", "de", "fgh"] → {1=2, 2=2, 3=1}

 * 순회할 때마다 getOrDefault(len, 0) + 1로 개수를 증가

 * 동시에 max 값을 갱신해 최댓값을 즉시 저장
   → 마지막에 따로 최대값을 구할 필요가 없음

 * 최종적으로 max를 반환하면 가장 큰 그룹의 크기가 된다
 */

import java.util.*;

class Solution {
    
    public int solution(String[] strArr) {
        
        // 문자열 길이별 개수를 저장할 Map (key = 문자열 길이, value = 해당 길이의 문자열 개수)
        Map<Integer, Integer> countByLen = new HashMap<>();
        
        // 가장 큰 그룹의 크기를 저장할 변수
        int max = 0;

        // 주어진 문자열 배열 순회
        for (String s : strArr) {
            int len = s.length(); // 현재 문자열의 길이 구하기
            
            // 해당 길이의 기존 개수 가져오고 +1
            int c = countByLen.getOrDefault(len, 0) + 1;
            
            // Map에 다시 업데이트
            countByLen.put(len, c);
            
            // 지금까지의 최대 그룹 크기 갱신
            if (c > max) {
                max = c;
            }
        }

        // 가장 많은 그룹의 크기 반환
        return max;
    }
}