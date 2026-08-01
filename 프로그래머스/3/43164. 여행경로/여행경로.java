import java.util.*;

class Solution {
    
    private Map<String, PriorityQueue<String>> map = new HashMap<>();
    private List<String> result = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        
        // 1. 그래프 생성 (출발지 -> 도착지 우선순위 큐)
        for (String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

        // 2. ICN 공항부터 DFS 탐색 시작
        dfs("ICN");

        // 3. 후위 순회 결과를 역순으로 뒤집음
        Collections.reverse(result);

        return result.toArray(new String[0]);
    }

    private void dfs(String now) {
        
        PriorityQueue<String> pq = map.get(now);

        // 현재 공항에서 출발하는 항공권이 남아있는 동안 계속 이동
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll());
        }

        // 더 이상 갈 수 있는 공항이 없으면 결과 리스트에 담음 (후위 순회)
        result.add(now);
    }
}