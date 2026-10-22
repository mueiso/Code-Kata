import java.util.*;

class Solution {
    
    static class Edge implements Comparable<Edge> {
        
        int u, v, cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private int[] parent;

    private int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    private boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootJ] = rootI;
            return true;
        }
        return false;
    }

    public int solution(int[][] land, int height) {
        
        int n = land.length;
        int[][] group = new int[n][n];
        int groupIdx = 0;

        // 1. BFS를 이용해 높이 차이가 height 이하인 영역들을 그룹화
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (group[r][c] == 0) {
                    groupIdx++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{r, c});
                    group[r][c] = groupIdx;

                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        int cr = curr[0];
                        int cc = curr[1];

                        for (int i = 0; i < 4; i++) {
                            int nr = cr + dr[i];
                            int nc = cc + dc[i];

                            if (nr >= 0 && nr < n && nc >= 0 && nc < n && group[nr][nc] == 0) {
                                if (Math.abs(land[cr][cc] - land[nr][nc]) <= height) {
                                    group[nr][nc] = groupIdx;
                                    q.offer(new int[]{nr, nc});
                                }
                            }
                        }
                    }
                }
            }
        }

        // 2. 서로 다른 그룹 간의 경계선(사다리)을 찾아 에지 리스트 생성
        List<Edge> edges = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // 오른쪽과 아래쪽만 확인하여 중복 방지
                if (c + 1 < n && group[r][c] != group[r][c + 1]) {
                    int cost = Math.abs(land[r][c] - land[r][c + 1]);
                    edges.add(new Edge(group[r][c], group[r][c + 1], cost));
                }
                if (r + 1 < n && group[r][c] != group[r + 1][c]) {
                    int cost = Math.abs(land[r][c] - land[r + 1][c]);
                    edges.add(new Edge(group[r][c], group[r + 1][c], cost));
                }
            }
        }

        // 3. 크루스칼 알고리즘을 위해 사다리 비용 기준 오름차순 정렬
        Collections.sort(edges);

        // Union-Find 초기화
        parent = new int[groupIdx + 1];
        for (int i = 1; i <= groupIdx; i++) {
            parent[i] = i;
        }

        // 4. 최소 신장 트리(MST)를 구성하며 최소 사다리 비용 합산
        int totalCost = 0;
        int edgeCount = 0;

        for (Edge edge : edges) {
            if (union(edge.u, edge.v)) {
                totalCost += edge.cost;
                edgeCount++;
                if (edgeCount == groupIdx - 1) break; // 모든 그룹이 연결되면 종료
            }
        }

        return totalCost;
    }
}