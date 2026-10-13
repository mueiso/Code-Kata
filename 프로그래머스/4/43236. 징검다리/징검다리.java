import java.util.Arrays;

class Solution {
    
    public int solution(int distance, int[] rocks, int n) {
        
        // 1. 바위의 위치를 오름차순으로 정렬
        Arrays.sort(rocks);

        long left = 1;
        long right = distance;
        int answer = 0;

        // 2. 이분 탐색 수행
        while (left <= right) {
            long mid = (left + right) / 2;

            int removed = 0;
            long prev = 0;

            for (int rock : rocks) {
                // 현재 바위와 이전 지점 사이의 거리가 mid보다 작으면 바위 제거
                if (rock - prev < mid) {
                    removed++;
                } else {
                    prev = rock; // 바위 유지
                }
            }

            // 마지막 바위와 도착 지점 사이의 거리 확인
            if (distance - prev < mid) {
                removed++;
            }

            // 3. 제거된 바위의 수가 n 이하인지 확인
            if (removed <= n) {
                answer = (int) mid; // 가능한 최솟값 저장
                left = mid + 1;     // 더 큰 거리 시도
            } else {
                right = mid - 1;    // 거리가 너무 크므로 줄이기
            }
        }

        return answer;
    }
}