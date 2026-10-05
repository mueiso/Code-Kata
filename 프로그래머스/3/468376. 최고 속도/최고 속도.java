import java.util.*;

class Solution {
    
    static class Point {
        
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            
            int h = (int) (x ^ (x >>> 32));
            
            return 31 * h + (int) (y ^ (y >>> 32));
        }
    }

    private Map<Point, Integer> pointToId = new HashMap<>();
    private List<Point> idToPoint = new ArrayList<>();
    private List<Integer> nodeLimit = new ArrayList<>();
    private static final int INF = 2_000_000_000;

    private int getOrCreateNode(Point p) {
        
        Integer id = pointToId.get(p);
        if (id == null) {
            id = idToPoint.size();
            pointToId.put(p, id);
            idToPoint.add(p);
            nodeLimit.add(INF);
        }
        
        return id;
    }

    public int[] solution(int[][] city, int[][] road) {
        
        int n = city.length;
        int m = road.length;

        // 1. 도시 노드 등록
        int[] cityNodeId = new int[n];
        for (int i = 0; i < n; i++) {
            Point p = new Point(city[i][0], city[i][1]);
            cityNodeId[i] = getOrCreateNode(p);
        }

        // 2. 도로 끝점 및 카메라 위치 등록
        for (int j = 0; j < m; j++) {
            long x1 = road[j][0];
            long y1 = road[j][1];
            long x2 = road[j][2];
            long y2 = road[j][3];
            int limit = road[j][4];

            getOrCreateNode(new Point(x1, y1));
            getOrCreateNode(new Point(x2, y2));

            Point camPoint = (y1 == y2) ? new Point((x1 + x2) / 2, y1) : new Point(x1, (y1 + y2) / 2);
            int camId = getOrCreateNode(camPoint);
            nodeLimit.set(camId, Math.min(nodeLimit.get(camId), limit));
        }

        // 3. 도로별 노드 수집 및 인접 리스트 간선 구성
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            long x1_i = road[i][0];
            long y1_i = road[i][1];
            long x2_i = road[i][2];
            long y2_i = road[i][3];

            boolean isHorizontal = (y1_i == y2_i);
            List<Point> keyPoints = new ArrayList<>();

            // 3-1. 도로 끝점 및 카메라 위치 추가
            keyPoints.add(new Point(x1_i, y1_i));
            keyPoints.add(new Point(x2_i, y2_i));
            keyPoints.add(isHorizontal ? new Point((x1_i + x2_i) / 2, y1_i) : new Point(x1_i, (y1_i + y2_i) / 2));

            // 3-2. 도로 위에 존재하는 도시 추가
            for (int c = 0; c < n; c++) {
                long cx = city[c][0];
                long cy = city[c][1];
                if (isHorizontal) {
                    if (cy == y1_i && cx >= x1_i && cx <= x2_i) {
                        keyPoints.add(new Point(cx, cy));
                    }
                } else {
                    if (cx == x1_i && cy >= y1_i && cy <= y2_i) {
                        keyPoints.add(new Point(cx, cy));
                    }
                }
            }

            // 3-3. 다른 도로와의 교차점 추가
            for (int j = 0; j < m; j++) {
                if (i == j) continue;
                long x1_j = road[j][0];
                long y1_j = road[j][1];
                long x2_j = road[j][2];
                long y2_j = road[j][3];
                boolean isHorizontalJ = (y1_j == y2_j);

                if (isHorizontal && !isHorizontalJ) {
                    if (x1_i <= x1_j && x1_j <= x2_i && y1_j <= y1_i && y1_i <= y2_j) {
                        keyPoints.add(new Point(x1_j, y1_i));
                    }
                } else if (!isHorizontal && isHorizontalJ) {
                    if (y1_i <= y1_j && y1_j <= y2_i && x1_j <= x1_i && x1_i <= x2_j) {
                        keyPoints.add(new Point(x1_i, y1_j));
                    }
                }
            }

            // 3-4. 도로 진행 방향에 따라 정렬
            if (isHorizontal) {
                keyPoints.sort((p1, p2) -> Long.compare(p1.x, p2.x));
            } else {
                keyPoints.sort((p1, p2) -> Long.compare(p1.y, p2.y));
            }

            // 3-5. 중복 제거 및 간선 생성
            List<Point> uniquePoints = new ArrayList<>();
            for (Point p : keyPoints) {
                if (uniquePoints.isEmpty() || !uniquePoints.get(uniquePoints.size() - 1).equals(p)) {
                    uniquePoints.add(p);
                }
            }

            for (int k = 0; k < uniquePoints.size() - 1; k++) {
                int u = getOrCreateNode(uniquePoints.get(k));
                int v = getOrCreateNode(uniquePoints.get(k + 1));

                while (adj.size() <= Math.max(u, v)) {
                    adj.add(new ArrayList<>());
                }
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
        }

        int numNodes = idToPoint.size();
        while (adj.size() < numNodes) {
            adj.add(new ArrayList<>());
        }

        // 4. 최대 병목 경로 다익스트라 탐색
        int[] maxBottleneck = new int[numNodes];
        Arrays.fill(maxBottleneck, -1);

        int startNode = cityNodeId[0];
        maxBottleneck[startNode] = nodeLimit.get(startNode);

        // Max-Heap: 병목 속도가 큰 순서대로 탐색
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        pq.offer(new int[]{startNode, maxBottleneck[startNode]});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int b = curr[1];

            if (b < maxBottleneck[u]) continue;

            for (int v : adj.get(u)) {
                int nextB = Math.min(b, nodeLimit.get(v));
                if (nextB > maxBottleneck[v]) {
                    maxBottleneck[v] = nextB;
                    pq.offer(new int[]{v, nextB});
                }
            }
        }

        // 5. 정답 배열 생성 (2번 ~ n번 도시)
        int[] answer = new int[n - 1];
        for (int i = 1; i < n; i++) {
            int targetNode = cityNodeId[i];
            int ans = maxBottleneck[targetNode];
            answer[i - 1] = (ans == INF) ? 0 : ans;
        }

        return answer;
    }
}