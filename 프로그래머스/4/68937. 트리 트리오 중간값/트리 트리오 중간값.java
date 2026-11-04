import java.util.*;

class Solution {
    
    static class Result {
        
        int farthestNode;
        int maxDist;
        int count;

        Result(int farthestNode, int maxDist, int count) {
            this.farthestNode = farthestNode;
            this.maxDist = maxDist;
            this.count = count;
        }
    }

    public int solution(int n, int[][] edges) {
        
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // 1. 임의의 정점(1)에서 가장 먼 정점 A 찾기
        Result res1 = bfs(1, n, adj);
        int nodeA = res1.farthestNode;

        // 2. 정점 A에서 가장 먼 정점 B 및 트리의 지름(maxDist) 구하기
        Result res2 = bfs(nodeA, n, adj);
        int nodeB = res2.farthestNode;
        int maxDist = res2.maxDist;

        // A에서 가장 먼 정점이 2개 이상이면 지름이 여러 개 존재하므로 maxDist 반환
        if (res2.count >= 2) {
            return maxDist;
        }

        // 3. A에서 가장 먼 정점이 1개뿐이라면, 그 정점 B에서 다시 가장 먼 정점들 찾기
        Result res3 = bfs(nodeB, n, adj);

        // B에서 가장 먼 정점이 2개 이상이면 maxDist 반환, 아니면 maxDist - 1 반환
        if (res3.count >= 2) {
            return maxDist;
        } else {
            return maxDist - 1;
        }
    }

    /* BFS를 통해 특정 시작점에서 가장 먼 정점, 최대 거리, 그리고 해당 거리를 가진 정점의 개수를 반환하는 메서드 */
    private Result bfs(int start, int n, List<List<Integer>> adj) {
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        dist[start] = 0;

        int farthestNode = start;
        int maxDist = 0;
        int count = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (dist[curr] > maxDist) {
                maxDist = dist[curr];
                farthestNode = curr;
                count = 1;
            } else if (dist[curr] == maxDist) {
                count++;
            }

            for (int next : adj.get(curr)) {
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    q.offer(next);
                }
            }
        }

        return new Result(farthestNode, maxDist, count);
    }
}