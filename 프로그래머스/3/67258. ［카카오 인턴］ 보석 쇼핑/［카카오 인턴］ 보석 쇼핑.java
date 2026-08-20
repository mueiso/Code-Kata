import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    
    public int[] solution(String[] gems) {
        
        // 1. 전체 보석 종류 개수 파악
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int totalTypes = gemTypes.size();

        // 2. 슬라이딩 윈도우 / 투 포인터 탐색
        Map<String, Integer> map = new HashMap<>();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int startRes = 0, endRes = 0;

        for (int right = 0; right < gems.length; right++) {
            // right 포인터 보석 추가
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);

            // 모든 보석 종류를 포함하고 있다면 left 포인터를 당겨서 최소 구간 축소 시도
            while (map.size() == totalTypes) {
                // 더 짧은 구간을 찾은 경우 갱신 (1-based index)
                if (right - left < minLen) {
                    minLen = right - left;
                    startRes = left + 1;
                    endRes = right + 1;
                }

                // left 포인터의 보석 차감
                map.put(gems[left], map.get(gems[left]) - 1);
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            }
        }

        return new int[]{startRes, endRes};
    }
}