import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        
        int idx;
        int time;
        int state;

        Node(int idx, int time, int state) {
            this.idx = idx;
            this.time = time;
            this.state = state;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static class Edge {
        int to;
        int time;
        int d; // 0: 정방향, 1: 역방향

        Edge(int to, int time, int d) {
            this.to = to;
            this.time = time;
            this.d = d;
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        
        List<Edge>[] graph = new ArrayList[n + 1];
        List<Edge>[] revGraph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }

        for (int[] r : roads) {
            graph[r[0]].add(new Edge(r[1], r[2], 0));
            revGraph[r[1]].add(new Edge(r[0], r[2], 1));
        }

        Map<Integer, Integer> trapMap = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapMap.put(traps[i], i);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] dist = new int[n + 1][1 << traps.length];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        pq.offer(new Node(start, 0, 0));
        dist[start][0] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.idx == end) {
                return curr.time;
            }

            if (curr.time > dist[curr.idx][curr.state]) {
                continue;
            }

            boolean isCurrTrap = trapMap.containsKey(curr.idx);
            int currTrapIdx = isCurrTrap ? trapMap.get(curr.idx) : -1;
            boolean currTrapActive = isCurrTrap && ((curr.state & (1 << currTrapIdx)) != 0);

            // 정방향 간선들과 역방향 간선들을 모두 확인
            List<Edge> combined = new ArrayList<>();
            for (Edge e : graph[curr.idx]) combined.add(e);
            for (Edge e : revGraph[curr.idx]) combined.add(e);

            for (Edge e : combined) {
                boolean isNextTrap = trapMap.containsKey(e.to);
                int nextTrapIdx = isNextTrap ? trapMap.get(e.to) : -1;
                boolean nextTrapActive = isNextTrap && ((curr.state & (1 << nextTrapIdx)) != 0);

                // 두 정점 중 하나의 함정만 활성화되어 있는 경우 간선 방향이 뒤집힘
                boolean reversed = currTrapActive ^ nextTrapActive;

                if ((!reversed && e.d == 0) || (reversed && e.d == 1)) {
                    int nextState = curr.state;
                    if (isNextTrap) {
                        nextState ^= (1 << nextTrapIdx);
                    }

                    int nextTime = curr.time + e.time;
                    if (nextTime < dist[e.to][nextState]) {
                        dist[e.to][nextState] = nextTime;
                        pq.offer(new Node(e.to, nextTime, nextState));
                    }
                }
            }
        }

        return -1;
    }
}