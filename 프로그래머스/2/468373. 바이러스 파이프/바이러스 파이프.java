import java.util.*;

class Solution {
    
    private int maxInfected = 0;
    private List<List<Integer>>[] components;

    public int solution(int n, int infection, int[][] edges, int k) {
        
        maxInfected = 0;
        components = new ArrayList[4];

        // 1. 파이프 종류(1~3)별 연결 요소 미리 구하기
        for (int type = 1; type <= 3; type++) {
            components[type] = buildComponents(n, edges, type);
        }

        // 2. 초기 감염 상태 설정 (1-based index)
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;

        // 3. 백트래킹 탐색 시작
        dfs(infected, 1, 0, 0, k, n);

        return maxInfected;
    }

    /* DSU를 이용해 특정 파이프 타입으로 연결된 노드 그룹 생성 */
    private List<List<Integer>> buildComponents(int n, int[][] edges, int targetType) {
        
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int type = edge[2];

            if (type == targetType) {
                union(parent, u, v);
            }
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int root = find(parent, i);
            map.computeIfAbsent(root, x -> new ArrayList<>()).add(i);
        }

        return new ArrayList<>(map.values());
    }

    private int find(int[] parent, int i) {
        
        if (parent[i] == i) return i;
        
        return parent[i] = find(parent, parent[i]);
    }

    private void union(int[] parent, int i, int j) {
        
        int rootI = find(parent, i);
        int rootJ = find(parent, j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }

    /* 백트래킹 (DFS) */
    private void dfs(boolean[] infected, int currentCount, int lastType, int depth, int k, int n) {
        
        maxInfected = Math.max(maxInfected, currentCount);

        // 최대 행동 횟수 달성 또는 모든 배양체 감염 완료 시 종료
        if (depth == k || currentCount == n) {
            return;
        }

        for (int type = 1; type <= 3; type++) {
            // 직전에 연 파이프와 같은 종류는 연속으로 열 필요 없음
            if (type == lastType) continue;

            boolean[] nextInfected = infected.clone();
            int nextCount = currentCount;

            // 해당 파이프 종류로 연결된 그룹 순회
            for (List<Integer> comp : components[type]) {
                boolean hasInfected = false;

                // 그룹 내에 감염된 배양체가 있는지 확인
                for (int node : comp) {
                    if (infected[node]) {
                        hasInfected = true;
                        break;
                    }
                }

                // 감염된 배양체가 있다면 해당 그룹의 전체 배양체 감염
                if (hasInfected) {
                    for (int node : comp) {
                        if (!nextInfected[node]) {
                            nextInfected[node] = true;
                            nextCount++;
                        }
                    }
                }
            }

            // 새로 감염된 배양체가 발생한 경우에만 다음 단계로 재귀 호출
            if (nextCount > currentCount) {
                dfs(nextInfected, nextCount, type, depth + 1, k, n);
            }
        }
    }
}