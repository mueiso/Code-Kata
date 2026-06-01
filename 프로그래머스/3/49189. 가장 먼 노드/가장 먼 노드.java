import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        
        // 1. 인접 리스트로 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            int u = e[0];
            int v = e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 2. 거리 기록용 배열 (-1로 초기화)
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        // 3. BFS 시작
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        dist[1] = 0; // 1번 노드 자기 자신까지의 거리는 0

        int maxDist = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : graph.get(curr)) {
                // 아직 방문하지 않은 노드라면
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[curr] + 1;
                    maxDist = Math.max(maxDist, dist[neighbor]);
                    queue.add(neighbor);
                }
            }
        }

        // 4. 가장 먼 거리(maxDist)와 같은 거리를 가진 노드 개수 세기
        int answer = 0;
        for (int d : dist) {
            if (d == maxDist) answer++;
        }

        return answer;
    }
}