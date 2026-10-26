import java.util.*;

class Solution {
    
    public boolean solution(int n, int[][] path, int[][] order) {
        
        // 1. 트리 인접 리스트 구성
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] p : path) {
            adj.get(p[0]).add(p[1]);
            adj.get(p[1]).add(p[0]);
        }

        // 2. 선행 방문 조건 배열 설정 (before[B] = A : A를 방문해야 B를 방문 가능)
        int[] before = new int[n];
        Arrays.fill(before, -1);
        for (int[] o : order) {
            before[o[1]] = o[0];
        }

        // 0번 방에 선행 조건이 있으면 탐색 불가
        if (before[0] != -1) {
            return false;
        }

        boolean[] visited = new boolean[n];
        int[] waiting = new int[n];
        Arrays.fill(waiting, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        int visitedCount = 1;

        // 3. BFS 탐색 수행
        while (!q.isEmpty()) {
            int curr = q.poll();

            // 현재 방을 방문했으므로, 이 방을 선행 조건으로 기다리던 방이 있다면 해제
            if (waiting[curr] != -1) {
                int target = waiting[curr];
                visited[target] = true;
                visitedCount++;
                q.offer(target);
            }

            for (int next : adj.get(curr)) {
                if (visited[next]) continue;

                int req = before[next];
                // 선행 조건이 충족되지 않았다면 대기
                if (req != -1 && !visited[req]) {
                    waiting[req] = next;
                } else {
                    visited[next] = true;
                    visitedCount++;
                    q.offer(next);
                }
            }
        }

        // 모든 방을 방문했는지 확인
        return visitedCount == n;
    }
}