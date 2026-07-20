class Solution {
    
    public int solution(int n, int[] stations, int w) {
        
        int answer = 0;
        int position = 1;         // 전파가 안 통하는 첫 아파트 번호
        int range = 2 * w + 1;    // 5G 기지국 하나가 커버하는 총 넓이

        for (int station : stations) {
            int startCoverage = station - w; // 현재 기지국의 전파 시작점

            // 기지국 전파 시작점보다 현재 위치가 앞에 있다면 빈 구간 존재
            if (position < startCoverage) {
                int gap = startCoverage - position;
                // gap을 커버하기 위해 필요한 최소 기지국 수 (정수 올림 연산)
                answer += (gap + range - 1) / range;
            }

            // 다음 탐색 위치를 현재 기지국의 커버리지 바로 다음으로 이동
            position = station + w + 1;
        }

        // 마지막 기지국 이후 ~ N번 아파트 사이에 전파가 안 통하는 구간이 남은 경우
        if (position <= n) {
            int gap = n - position + 1;
            answer += (gap + range - 1) / range;
        }

        return answer;
    }
}