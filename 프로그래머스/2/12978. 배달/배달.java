import java.util.*;

class Solution {
    
    // 다익스트라 탐색 시 사용할 노드 클래스
    static class Node implements Comparable<Node> {
        
        int id;     // 마을 번호
        int weight; // 1번 마을로부터의 누적 시간 (혹은 간선 가중치)
        
        Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
        
        /* 우선순위 큐에서 시간이 짧은 순으로 정렬되도록 설정 */
        @Override
        public int compareTo(Node o) {
            
            return this.weight - o.weight;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        
        // 1. 그래프를 인접 리스트로 구현
        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 양방향 도로 정보 추가 (중복 도로가 있어도 리스트 구조는 안전함)
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        // 2. 최단 거리 배열 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0; // 시작점인 1번 마을은 거리가 0
        
        // 3. 다익스트라 알고리즘 수행
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            
            // 이미 더 짧은 경로를 찾아서 처리된 마을이라면 스킵
            if (curr.weight > dist[curr.id]) {
                continue;
            }
            
            // 현재 마을과 연결된 이웃 마을들 확인
            for (Node neighbor : graph[curr.id]) {
                int nextDist = dist[curr.id] + neighbor.weight;
                
                // 현재 마을을 거쳐서 가는 것이 더 빠르다면 최단 거리 갱신
                if (nextDist < dist[neighbor.id]) {
                    dist[neighbor.id] = nextDist;
                    pq.add(new Node(neighbor.id, nextDist));
                }
            }
        }
        
        // 4. K 시간 이하로 배달 가능한 마을 개수 카운트
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        
        return answer;
    }
}