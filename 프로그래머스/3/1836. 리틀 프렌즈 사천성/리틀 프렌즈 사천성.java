import java.util.*;

class Solution {
    
    private static class Point {
        
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public String solution(int m, int n, String[] board) {
        
        char[][] grid = new char[m][n];
        Map<Character, List<Point>> map = new HashMap<>();

        // 1. 타일 위치 정보 집계
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = board[i].charAt(j);
                if (grid[i][j] >= 'A' && grid[i][j] <= 'Z') {
                    map.putIfAbsent(grid[i][j], new ArrayList<>());
                    map.get(grid[i][j]).add(new Point(i, j));
                }
            }
        }

        // 2. 알파벳 오름차순 정렬
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();

        // 3. 탐욕적 타일 제거 시뮬레이션
        while (!list.isEmpty()) {
            boolean removed = false;

            for (int i = 0; i < list.size(); i++) {
                char ch = list.get(i);
                List<Point> pts = map.get(ch);
                Point p1 = pts.get(0);
                Point p2 = pts.get(1);

                if (canConnect(grid, p1, p2, ch)) {
                    grid[p1.r][p1.c] = '.';
                    grid[p2.r][p2.c] = '.';
                    sb.append(ch);
                    list.remove(i);
                    removed = true;
                    break; // 최우선 알파벳을 제거했으므로 다시 처음부터 탐색
                }
            }

            // 한 바퀴 동안 아무 타일도 제거하지 못했다면 불가능
            if (!removed) {
                return "IMPOSSIBLE";
            }
        }

        return sb.toString();
    }

    /* 두 타일 간 연결 가능 여부 판단 (최대 1회 꺾임) */
    private boolean canConnect(char[][] grid, Point p1, Point p2, char ch) {
        
        int r1 = p1.r, c1 = p1.c;
        int r2 = p2.r, c2 = p2.c;

        // 1. 직선 경로 (동일 행)
        if (r1 == r2) {
            return isRowClear(grid, r1, c1, c2, ch);
        }

        // 2. 직선 경로 (동일 열)
        if (c1 == c2) {
            return isColClear(grid, c1, r1, r2, ch);
        }

        // 3. 1회 꺾임 경로 A: (r1, c1) -> (r1, c2) -> (r2, c2)
        if (grid[r1][c2] == '.' && isRowClear(grid, r1, c1, c2, ch) && isColClear(grid, c2, r1, r2, ch)) {
            return true;
        }

        // 4. 1회 꺾임 경로 B: (r1, c1) -> (r2, c1) -> (r2, c2)
        if (grid[r2][c1] == '.' && isColClear(grid, c1, r1, r2, ch) && isRowClear(grid, r2, c1, c2, ch)) {
            return true;
        }

        return false;
    }

    private boolean isRowClear(char[][] grid, int r, int c1, int c2, char target) {
        
        int minC = Math.min(c1, c2);
        int maxC = Math.max(c1, c2);
        for (int c = minC; c <= maxC; c++) {
            if (grid[r][c] != '.' && grid[r][c] != target) {
                return false;
            }
        }
        
        return true;
    }

    private boolean isColClear(char[][] grid, int c, int r1, int r2, char target) {
        
        int minR = Math.min(r1, r2);
        int maxR = Math.max(r1, r2);
        for (int r = minR; r <= maxR; r++) {
            if (grid[r][c] != '.' && grid[r][c] != target) {
                return false;
            }
        }
        
        return true;
    }
}