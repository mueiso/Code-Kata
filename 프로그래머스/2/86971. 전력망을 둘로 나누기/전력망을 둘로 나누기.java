/*
 * 트리의 특징
   노드 n개
   간선 n-1개
   사이클 없음
   ➡️ 간선 하나를 끊으면 반드시 두 개의 트리로 
   ➡️ 트리 → 간선 하나 제거하면 두 개의 연결 요소
   
 * 각 간선을 끊을 때:
   DFS or BFS로 한 쪽 컴포넌트의 노드 수를 센다
   다른 쪽은 n - count
   두 값의 차이 |count - (n - count)| 계산
   최소값 갱신
   
 * 트리는 연결 그래프이므로 간선을 하나 제거하면 → 단순 연결요소 개수 세기 문제로 변환됨
   연결 요소 크기만 구하면 됨
 */

import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        
        int answer = Integer.MAX_VALUE;

        // 1. 인접 리스트 생성
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 2. 모든 간선에 대해 하나씩 제거 시도
        for (int i = 0; i < wires.length; i++) {

            // 그래프 초기화
            for (int j = 1; j <= n; j++) {
                graph[j].clear();
            }

            // i번째 간선을 제외하고 그래프 구성
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;

                int a = wires[j][0];
                int b = wires[j][1];
                graph[a].add(b);
                graph[b].add(a);
            }

            visited = new boolean[n + 1];

            // 3. 한쪽 네트워크 크기 계산 (DFS)
            int count = dfs(1);

            // 4. 두 네트워크 차이 계산
            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);
        }

        return answer;
    }

    // DFS로 연결된 노드 수 세기
    private int dfs(int node) {
        
        visited[node] = true;
        int count = 1;

        for (int next : graph[node]) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        return count;
    }
}