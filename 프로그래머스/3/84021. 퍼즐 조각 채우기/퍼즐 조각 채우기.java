import java.util.*;

class Solution {
    
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static class Point implements Comparable<Point> {
        
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            
            return Integer.compare(this.x, o.x);
        }

        @Override
        public boolean equals(Object o) {
            
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            
            return Objects.hash(x, y);
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        
        int answer = 0;
        int n = game_board.length;

        // 1. game_board의 빈 공간(0) 추출 및 table의 퍼즐 조각(1) 추출
        List<List<Point>> boardShapes = extractShapes(game_board, 0);
        List<List<Point>> tableShapes = extractShapes(table, 1);

        boolean[] usedTableShape = new boolean[tableShapes.size()];

        // 2. 보드의 빈 공간마다 알맞은 퍼즐 조각 찾기
        for (List<Point> boardShape : boardShapes) {
            for (int i = 0; i < tableShapes.size(); i++) {
                if (usedTableShape[i]) continue;

                List<Point> tableShape = tableShapes.get(i);

                // 칸 수(크기)가 다르면 패스
                if (boardShape.size() != tableShape.size()) continue;

                // 3. 4방향 회전하며 일치 여부 검사
                if (isMatch(boardShape, tableShape)) {
                    answer += boardShape.size();
                    usedTableShape[i] = true;
                    break;
                }
            }
        }

        return answer;
    }

    // BFS를 이용하여 대상 값(target)을 가진 영역들을 추출하고 정규화
    private List<List<Point>> extractShapes(int[][] grid, int target) {
        
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        List<List<Point>> shapes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == target && !visited[i][j]) {
                    List<Point> shape = new ArrayList<>();
                    Queue<Point> queue = new ArrayDeque<>();

                    queue.offer(new Point(i, j));
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        Point curr = queue.poll();
                        shape.add(curr);

                        for (int d = 0; d < 4; d++) {
                            int nx = curr.x + dx[d];
                            int ny = curr.y + dy[d];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                                if (grid[nx][ny] == target && !visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                    queue.offer(new Point(nx, ny));
                                }
                            }
                        }
                    }
                    shapes.add(normalize(shape));
                }
            }
        }
        
        return shapes;
    }

    // 좌표 기준점을 (0, 0)으로 맞추고 오름차순 정렬
    private List<Point> normalize(List<Point> shape) {
        
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Point p : shape) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }

        List<Point> normalized = new ArrayList<>();
        for (Point p : shape) {
            normalized.add(new Point(p.x - minX, p.y - minY));
        }

        Collections.sort(normalized);
        
        return normalized;
    }

    // 90도 시계 방향 회전 후 정규화
    private List<Point> rotate(List<Point> shape) {
        
        List<Point> rotated = new ArrayList<>();
        for (Point p : shape) {
            rotated.add(new Point(p.y, -p.x));
        }
        
        return normalize(rotated);
    }

    // 0도, 90도, 180도, 270도 회전하며 보드 모양과 퍼즐 모양 일치 여부 확인
    private boolean isMatch(List<Point> boardShape, List<Point> tableShape) {
        
        List<Point> current = tableShape;

        for (int r = 0; r < 4; r++) {
            if (boardShape.equals(current)) {
                return true;
            }
            current = rotate(current);
        }
        
        return false;
    }
}