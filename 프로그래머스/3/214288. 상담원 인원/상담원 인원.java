import java.util.*;

class Solution {
    
    private int minTotalWaitTime = Integer.MAX_VALUE;
    private List<int[]>[] requestsByType;
    private int[][] waitTimeMemo; // waitTimeMemo[type][mentorCount]

    public int solution(int k, int n, int[][] reqs) {
        
        // 1. 상담 유형별 요청 분류 (1 ~ k)
        requestsByType = new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            requestsByType[i] = new ArrayList<>();
        }
        for (int[] req : reqs) {
            int type = req[2];
            requestsByType[type].add(req);
        }

        // 2. 대기 시간 미리 계산 (Memoization)
        // 유형별로 1명 ~ (n - k + 1)명 배정 시 대기 시간 측정
        int maxMentors = n - k + 1;
        waitTimeMemo = new int[k + 1][maxMentors + 1];

        for (int c = 1; c <= k; c++) {
            for (int m = 1; m <= maxMentors; m++) {
                waitTimeMemo[c][m] = simulate(requestsByType[c], m);
            }
        }

        // 3. 백트래킹으로 멘토 인원 배정 조합 탐색
        dfs(1, k, n, 0);

        return minTotalWaitTime;
    }

    /* 우선순위 큐를 활용한 대기 시간 시뮬레이션 */
    private int simulate(List<int[]> requests, int mentorCount) {
        
        if (requests.isEmpty()) return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int totalWaitTime = 0;

        for (int[] req : requests) {
            int start = req[0];
            int duration = req[1];

            if (pq.size() < mentorCount) {
                pq.offer(start + duration);
            } else {
                int earliestFinish = pq.poll();

                if (earliestFinish <= start) {
                    pq.offer(start + duration);
                } else {
                    totalWaitTime += (earliestFinish - start);
                    pq.offer(earliestFinish + duration);
                }
            }
        }

        return totalWaitTime;
    }

    /* 멘토 조합 탐색 (DFS) */
    private void dfs(int typeIndex, int k, int remainingMentors, int currentWaitTime) {
        
        if (currentWaitTime >= minTotalWaitTime) return; // 가지치기

        // 모든 유형에 배정 완료된 경우
        if (typeIndex > k) {
            minTotalWaitTime = Math.min(minTotalWaitTime, currentWaitTime);
            return;
        }

        // 마지막 유형에는 남은 멘토를 모두 배정
        if (typeIndex == k) {
            int wait = waitTimeMemo[typeIndex][remainingMentors];
            dfs(typeIndex + 1, k, 0, currentWaitTime + wait);
            return;
        }

        // 현재 유형에 1명부터 (남은 멘토 - 남은 유형 수)명까지 배정
        int maxAvailable = remainingMentors - (k - typeIndex);
        for (int m = 1; m <= maxAvailable; m++) {
            int wait = waitTimeMemo[typeIndex][m];
            dfs(typeIndex + 1, k, remainingMentors - m, currentWaitTime + wait);
        }
    }
}