import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        // 1. 인접 리스트 그래프 생성
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int u = path[0];
            int v = path[1];
            int w = path[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        // 2. 출입구 및 산봉우리 여부 빠른 조회를 위한 배열 설정
        boolean[] isSummit = new boolean[n + 1];
        for (int summit : summits) {
            isSummit[summit] = true;
        }

        // 3. 다익스트라 초기화
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // 모든 출입구를 시작점으로 PQ에 삽입 (Multi-source)
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new int[]{gate, 0});
        }

        // 4. 다익스트라 탐색
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];

            if (d > intensity[u]) continue;
            // 산봉우리에 도달하면 더 이상 다른 지점으로 이동하지 않음
            if (isSummit[u]) continue;

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];

                // 경로 상의 최대 등산로 시간(intensity) 계산
                int nextIntensity = Math.max(d, w);
                if (nextIntensity < intensity[v]) {
                    intensity[v] = nextIntensity;
                    pq.offer(new int[]{v, nextIntensity});
                }
            }
        }

        // 5. 최솟값을 가지는 산봉우리 찾기 (번호가 작은 순서대로 정렬)
        Arrays.sort(summits);

        int minSummit = -1;
        int minIntensity = Integer.MAX_VALUE;

        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }
        }

        return new int[]{minSummit, minIntensity};
    }
}