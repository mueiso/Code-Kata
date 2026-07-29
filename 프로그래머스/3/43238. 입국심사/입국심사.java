import java.util.Arrays;

class Solution {
    
    public long solution(int n, int[] times) {
        
        // 심사 시간이 가장 오래 걸리는 심사관을 찾기 위해 정렬
        Arrays.sort(times);

        long left = 1;
        // 가장 비효율적인 경우: 가장 느린 심사관이 n명을 모두 처리할 때의 시간
        long right = (long) times[times.length - 1] * n;
        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long totalPeople = 0;

            // mid 시간 동안 각 심사관이 처리할 수 있는 사람 수의 합 계산
            for (int time : times) {
                totalPeople += mid / time;
                
                // 이미 n명을 넘어섰다면 오버플로우 방지 및 빠른 탈출
                if (totalPeople >= n) {
                    break;
                }
            }

            // n명 이상 심사할 수 있는 경우 (시간이 충분함)
            if (totalPeople >= n) {
                answer = mid;      // 현재 시간을 정답 후보로 저장
                right = mid - 1;  // 더 짧은 시간으로도 가능한지 탐색
            } else {
                // n명을 심사하기에 시간이 부족한 경우
                left = mid + 1;   // 시간을 늘려서 탐색
            }
        }

        return answer;
    }
}