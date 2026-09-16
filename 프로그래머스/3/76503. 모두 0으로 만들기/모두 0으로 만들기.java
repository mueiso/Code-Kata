import java.util.*;

class Solution {
    
    public long solution(int[] a, int[][] edges) {
        
        int n = a.length;

        // 1. 전체 가중치 합 계산 (long 타입 오버플로 방지)
        long totalSum = 0;
        long[] val = new long[n];
        for (int i = 0; i < n; i++) {
            val[i] = a[i];
            totalSum += a[i];
        }

        if (totalSum != 0) {
            return -1;
        }

        // 2. 인접 리스트로 트리 그래프 구축
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // 3. 0번 노드를 루트로 설정하여 BFS 순회 (부모 관계 및 방문 순서 기록)
        int[] parent = new int[n];
        int[] order = new int[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(0);
        visited[0] = true;
        int idx = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[idx++] = curr;

            for (int next : adj[curr]) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = curr;
                    queue.offer(next);
                }
            }
        }

        // 4. 역순 순회(자식 노드 -> 부모 노드)하며 가중치 전송 및 연산 횟수 누적
        long answer = 0;
        for (int i = n - 1; i > 0; i--) {
            int curr = order[i];
            int p = parent[curr];

            answer += Math.abs(val[curr]);
            val[p] += val[curr];
        }

        return answer;
    }
}