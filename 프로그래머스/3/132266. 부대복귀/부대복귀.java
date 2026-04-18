import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        // 1. 인접 리스트로 그래프 구성
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        
        // 2. 최단 거리 배열 초기화 (-1은 방문하지 않음 / 도달 불가 의미)
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        // 3. 목적지(destination)에서 시작하는 BFS
        Queue<Integer> q = new LinkedList<>();
        q.offer(destination);
        dist[destination] = 0;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int next : graph.get(curr)) {
                // 아직 방문하지 않은 지역이라면
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    q.offer(next);
                }
            }
        }
        
        // 4. 각 부대원의 위치(sources)에서 목적지까지의 최단 거리 매핑
        int[] answer = new int[sources.length];
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
}