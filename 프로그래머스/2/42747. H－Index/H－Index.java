import java.util.Arrays;

class Solution {
    
    public int solution(int[] citations) {
        
        Arrays.sort(citations); // 인용 수 오름차순 정렬
        int n = citations.length;

        for (int i = 0; i < n; i++) {
            int h = n - i; // i번째부터 끝까지 논문 수(=h편 후보)
            // h편이 각각 h번 이상 인용되려면 citations[i]가 h 이상이어야 함
            if (citations[i] >= h) {
                return h; // 처음 만족하는 h가 최댓값
            }
        }

        return 0; // 모두 조건을 만족하지 못 하면 H-Index는 0
    }
}