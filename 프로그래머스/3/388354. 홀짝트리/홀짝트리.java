import java.util.*;

class Solution {
    
    private int[] parent;

    private int find(int i) {
        
        if (parent[i] == i) return i;
        
        return parent[i] = find(parent[i]);
    }

    private void union(int i, int j) {
        
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }

    public int[] solution(int[] nodes, int[][] edges) {
        
        int maxNode = 0;
        for (int node : nodes) {
            maxNode = Math.max(maxNode, node);
        }

        parent = new int[maxNode + 1];
        int[] degree = new int[maxNode + 1];

        for (int node : nodes) {
            parent[node] = node;
        }

        // 1. 차수 계산 및 트리의 연결 요소 분리 (Union-Find)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            degree[u]++;
            degree[v]++;
            union(u, v);
        }

        // 2. 각 트리(연결 요소)별 노드 유형 개수 집계
        int[] countROE = new int[maxNode + 1]; // 비루트 기준 역홀짝 노드 수
        int[] countOE = new int[maxNode + 1];  // 비루트 기준 홀짝 노드 수

        for (int node : nodes) {
            int root = find(node);
            if ((node % 2) == (degree[node] % 2)) {
                countROE[root]++;
            } else {
                countOE[root]++;
            }
        }

        // 3. 트리별 홀짝 / 역홀짝 트리 가능 여부 판별
        Set<Integer> roots = new HashSet<>();
        for (int node : nodes) {
            roots.add(find(node));
        }

        int oeTreeCount = 0;
        int roeTreeCount = 0;

        for (int root : roots) {
            // countROE가 1개인 경우 -> 그 노드를 루트로 삼아 홀짝 트리 생성 가능
            if (countROE[root] == 1) {
                oeTreeCount++;
            }
            // countOE가 1개인 경우 -> 그 노드를 루트로 삼아 역홀짝 트리 생성 가능
            if (countOE[root] == 1) {
                roeTreeCount++;
            }
        }

        return new int[]{oeTreeCount, roeTreeCount};
    }
}