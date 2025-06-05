import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] solution(int[] emergency) {
        int n = emergency.length;
        int[] sorted = emergency.clone();
        Arrays.sort(sorted);  // 오름차순 정렬

        // 높은 응급도일수록 진료 순서(작은 숫자)가 되도록 매핑
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // sorted[i]는 i번째로 작은 값. 진료 순서는 (n - i).
            rankMap.put(sorted[i], n - i);
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = rankMap.get(emergency[i]);
        }

        return answer;
    }
}
