import java.util.*;

class Solution {
    
    /* 정점을 나타내는 클래스 */
    static class Vertex {
        
        int x, y;

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            
            return x == vertex.x && y == vertex.y;
        }

        @Override
        public int hashCode() {
            
            return Objects.hash(x, y);
        }
    }

    /* 간선을 나타내는 클래스 (양방향 구분을 위해 정렬 또는 두 점 저장) */
    static class Edge {
        
        Vertex v1, v2;

        Edge(Vertex v1, Vertex v2) {
            
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public boolean equals(Object o) {
            
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            
            return (v1.equals(edge.v1) && v2.equals(edge.v2)) || (v1.equals(edge.v2) && v2.equals(edge.v1));
        }

        @Override
        public int hashCode() {
            
            return v1.hashCode() + v2.hashCode();
        }
    }

    // 8방향 정의 (0부터 7까지) - 좌표를 2배로 확장하므로 이동량도 2배
    // 0: 위, 1: 우상, 2: 우, 3: 우하, 4: 하, 5: 좌하, 6: 좌, 7: 좌상
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    public int solution(int[] arrows) {
        
        int answer = 0;

        Vertex current = new Vertex(0, 0);
        Set<Vertex> visitedVertices = new HashSet<>();
        Set<Edge> visitedEdges = new HashSet<>();

        visitedVertices.add(current);

        for (int arrow : arrows) {
            // 대각선 교차 판정을 위해 한 번의 화살표 이동을 2번의 하프 스텝으로 나눔
            for (int i = 0; i < 2; i++) {
                int nextX = current.x + dx[arrow];
                int nextY = current.y + dy[arrow];
                Vertex next = new Vertex(nextX, nextY);

                Edge edge = new Edge(current, next);

                // 이미 방문한 정점인 경우
                if (visitedVertices.contains(next)) {
                    // 하지만 이 간선을 아직 지나간 적이 없다면 새로운 방이 생성됨
                    if (!visitedEdges.contains(edge)) {
                        answer++;
                    }
                } else {
                    // 처음 방문하는 정점이면 방문 처리
                    visitedVertices.add(next);
                }

                // 간선 방문 처리 및 현재 위치 갱신
                visitedEdges.add(edge);
                current = next;
            }
        }

        return answer;
    }
}