import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    
    public long solution(int n, int[] works) {
        
        long answer = 0;

        // 1. 모든 작업량의 합을 구합니다.
        long totalWork = 0;
        for (int work : works) {
            totalWork += work;
        }
        
        // 남은 시간 동안 모든 일을 다 끝낼 수 있다면 피로도는 0입니다.
        if (totalWork <= n) {
            return 0;
        }

        // 2. 가장 큰 작업량을 먼저 꺼내기 위해 최대 힙(Max-Heap)을 생성합니다.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }

        // 3. n시간 동안 매번 가장 많이 남은 작업량을 1씩 줄여 나갑니다.
        for (int i = 0; i < n; i++) {
            int maxWork = pq.poll();
            pq.offer(maxWork - 1);
        }

        // 4. 큐에 남아있는 최종 작업량들의 제곱의 합을 구합니다.
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += (long) work * work;
        }

        return answer;
    }
}