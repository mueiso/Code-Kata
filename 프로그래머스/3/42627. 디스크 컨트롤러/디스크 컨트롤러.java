import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    
    public int solution(int[][] jobs) {
        
        // 1. 요청 시각(s) 기준으로 작업 목록 정렬
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        // 2. 대기 큐: 소요 시간(l) 오름차순 정렬 (같으면 요청 시각 오름차순)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int totalTurnaroundTime = 0;
        int currentTime = 0;
        int jobIndex = 0;
        int completedCount = 0;

        // 모든 작업을 처리할 때까지 반복
        while (completedCount < jobs.length) {
            // 현재 시점(currentTime)까지 들어온 모든 요청을 대기 큐에 삽입
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= currentTime) {
                pq.offer(jobs[jobIndex]);
                jobIndex++;
            }

            // 대기 큐가 비어있다면 하드디스크가 쉬는 상태 -> 다음 작업 요청 시각으로 이동
            if (pq.isEmpty()) {
                currentTime = jobs[jobIndex][0];
            } else {
                // 가장 소요 시간이 짧은 작업 꺼내서 처리
                int[] currentJob = pq.poll();
                currentTime += currentJob[1]; // 작업 수행 후 현재 시간 갱신
                totalTurnaroundTime += (currentTime - currentJob[0]); // (종료 시각 - 요청 시각) 누적
                completedCount++;
            }
        }

        // 반환 시간 평균의 정수 부분
        return totalTurnaroundTime / jobs.length;
    }
}