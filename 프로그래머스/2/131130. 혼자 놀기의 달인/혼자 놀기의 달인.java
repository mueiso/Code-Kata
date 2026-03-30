/*
 * 방문 배열 사용
 * 모든 인덱스에서 시작해서 사이클 길이 계산
 * 길이들을 리스트에 저장
 * 정렬 후 큰 2개 선택
 */

import java.util.*;

class Solution {
    
    public int solution(int[] cards) {
        
        int n = cards.length;
        boolean[] visited = new boolean[n];
        List<Integer> groups = new ArrayList<>();

        // 1. 모든 위치에서 사이클 탐색
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            int count = 0;
            int cur = i;

            // 사이클 돌기
            while (!visited[cur]) {
                visited[cur] = true;
                cur = cards[cur] - 1; // 카드 값은 1-based
                count++;
            }

            groups.add(count);
        }

        // 2. 그룹이 2개 미만이면 점수 0
        if (groups.size() < 2) return 0;

        // 3. 내림차순 정렬
        Collections.sort(groups, Collections.reverseOrder());

        // 4. 가장 큰 두 개 곱하기
        return groups.get(0) * groups.get(1);
    }
}