import java.util.*;

class Solution {
    
    public int solution(int k, int[] tangerine) {

        // 1) 크기별 개수 세기
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int t : tangerine) {
            cnt.put(t, cnt.getOrDefault(t, 0) + 1);
        }

        // 2) 개수만 뽑아서 내림차순 정렬
        List<Integer> counts = new ArrayList<>(cnt.values());
        counts.sort(Collections.reverseOrder());

        // 3) 큰 개수부터 채우면서 k개를 만족할 때까지 종류 수 증가
        int typeCount = 0;
        int picked = 0;

        for (int c : counts) {
            picked += c;     // 해당 크기 귤을 전부 담는다고 가정(최소 종류를 위해)
            typeCount++;     // 종류 하나 추가

            if (picked >= k) break; // k개 이상 담았으면 종료
        }

        return typeCount;
    }
}