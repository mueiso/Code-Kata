class Solution {
    
    public int solution(int[] stones, int k) {
        
        int left = 1;
        int right = 200000000; // 디딤돌 숫자의 최댓값
        int answer = 0;

        // 이분 탐색 진행
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // mid명이 건널 수 있다면, 정답 후보로 기록하고 더 많은 인원을 시도
            if (canCross(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                // mid명이 건널 수 없다면, 인원수를 줄여서 시도
                right = mid - 1;
            }
        }

        return answer;
    }

    // m명의 친구가 징검다리를 무사히 건널 수 있는지 검증하는 함수
    private boolean canCross(int[] stones, int k, int m) {
        int consecutiveZeros = 0;

        for (int stone : stones) {
            // m번째 사람이 건널 때 밟을 수 없는 돌 (값이 m 미만인 돌)
            if (stone < m) {
                consecutiveZeros++;
                // 밟지 못하는 돌이 연속 k개 이상 이어지면 건너기 불가능
                if (consecutiveZeros >= k) {
                    return false;
                }
            } else {
                // 밟을 수 있는 돌을 만나면 연속 카운트 초기화
                consecutiveZeros = 0;
            }
        }

        return true;
    }
}