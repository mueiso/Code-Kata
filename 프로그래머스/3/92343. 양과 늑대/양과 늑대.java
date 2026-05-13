import java.util.*;

class Solution {
    
    private static int maxSheep;
    private static List<Integer>[] tree;

    public int solution(int[] info, int[][] edges) {
        
        maxSheep = 0;
        int n = info.length;
        tree = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 1. 트리 구조 생성 (부모 -> 자식)
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }

        // 2. 다음에 갈 수 있는 노드 리스트 생성
        List<Integer> nextNodes = new ArrayList<>();
        
        // 루트 노드(0번)부터 시작
        dfs(0, 0, 0, nextNodes, info);

        return maxSheep;
    }

    private void dfs(int node, int sheep, int wolf, List<Integer> nextNodes, int[] info) {
        
        // 현재 노드의 양/늑대 수 업데이트
        if (info[node] == 0) sheep++;
        else wolf++;

        // 늑대가 양을 잡아먹는 경우 종료
        if (wolf >= sheep) return;

        // 최대 양의 수 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 3. 다음으로 갈 수 있는 노드 후보군 업데이트
        List<Integer> list = new ArrayList<>(nextNodes);
        // 현재 노드의 자식들을 후보군에 추가
        for (int child : tree[node]) {
            list.add(child);
        }
        // 방금 방문한 노드는 후보군에서 제거
        list.remove(Integer.valueOf(node));

        // 4. 후보군에 있는 모든 노드를 하나씩 방문 시도
        for (int next : list) {
            dfs(next, sheep, wolf, list, info);
        }
    }
}