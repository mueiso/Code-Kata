import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    
    public int solution(int n, int[][] lighthouse) {
        
        // 등대가 2개면 무조건 1개만 켜면 됨
        if (n == 2) return 1;

        // 1. 인접 리스트 및 차수(degree) 배열 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] degree = new int[n + 1];
        for (int[] edge : lighthouse) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        // 2. 리프 노드(차수가 1인 노드)를 큐에 삽입
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 1) {
                q.offer(i);
            }
        }

        boolean[] lit = new boolean[n + 1]; // 등대가 켜졌는지 여부
        int answer = 0;

        // 3. 큐를 비우며 안쪽으로 탐색 (위상 정렬 스타일)
        while (!q.isEmpty()) {
            int curr = q.poll();

            // 현재 노드와 연결된 활성 상태의 이웃 노드 찾기
            for (int next : graph.get(curr)) {
                if (degree[next] > 0) { // 아직 끊어지지 않은 이웃
                    degree[next]--; // 연결을 끊음(차수 감소)
                    
                    // 이웃 노드가 새로운 리프 노드가 되면 큐에 추가
                    if (degree[next] == 1) {
                        q.offer(next);
                    }

                    // 핵심 그리디 로직: 나와 이웃 모두 안 켜져 있다면 이웃을 켠다!
                    if (!lit[curr] && !lit[next]) {
                        lit[next] = true;
                        answer++;
                    }
                    break; // 리프 노드이므로 활성 이웃은 1개뿐임
                }
            }
            // 현재 노드 처리 완료
            degree[curr] = 0;
        }

        return answer;
    }
}