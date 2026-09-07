import java.util.*;

class Solution {
    
    private static class Point {
        
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private int minAnswer = Integer.MAX_VALUE;
    private List<Integer> cardTypes = new ArrayList<>();
    private Map<Integer, List<Point>> cardPositions = new HashMap<>();

    public int solution(int[][] board, int r, int c) {
        
        // 1. 존재하는 카드 종류 및 위치 수집
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    int num = board[i][j];
                    if (!cardPositions.containsKey(num)) {
                        cardPositions.put(num, new ArrayList<>());
                        cardTypes.add(num);
                    }
                    cardPositions.get(num).add(new Point(i, j));
                }
            }
        }

        boolean[] visited = new boolean[cardTypes.size()];
        dfs(0, r, c, 0, board, visited);

        return minAnswer;
    }

    private void dfs(int depth, int r, int c, int totalCost, int[][] board, boolean[] visited) {
        
        if (totalCost >= minAnswer) return; // 가지치기

        // 모든 카드를 제거한 경우 최솟값 갱신
        if (depth == cardTypes.size()) {
            minAnswer = Math.min(minAnswer, totalCost);
            return;
        }

        for (int i = 0; i < cardTypes.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;

                int num = cardTypes.get(i);
                Point p1 = cardPositions.get(num).get(0);
                Point p2 = cardPositions.get(num).get(1);

                // 경우의 수 1: p1 -> p2 순서로 방문
                int cost1 = bfs(board, r, c, p1.r, p1.c) + bfs(board, p1.r, p1.c, p2.r, p2.c) + 2;
                
                board[p1.r][p1.c] = 0;
                board[p2.r][p2.c] = 0;
                dfs(depth + 1, p2.r, p2.c, totalCost + cost1, board, visited);
                board[p1.r][p1.c] = num;
                board[p2.r][p2.c] = num;

                // 경우의 수 2: p2 -> p1 순서로 방문
                int cost2 = bfs(board, r, c, p2.r, p2.c) + bfs(board, p2.r, p2.c, p1.r, p1.c) + 2;

                board[p1.r][p1.c] = 0;
                board[p2.r][p2.c] = 0;
                dfs(depth + 1, p1.r, p1.c, totalCost + cost2, board, visited);
                board[p1.r][p1.c] = num;
                board[p2.r][p2.c] = num;

                visited[i] = false;
            }
        }
    }

    private int bfs(int[][] board, int srcR, int srcC, int dstR, int dstC) {
        
        if (srcR == dstR && srcC == dstC) return 0;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][4];

        queue.offer(new int[]{srcR, srcC, 0});
        visited[srcR][srcC] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cr = curr[0];
            int cc = curr[1];
            int dist = curr[2];

            if (cr == dstR && cc == dstC) return dist;

            for (int i = 0; i < 4; i++) {
                // 1) 1칸 이동
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, dist + 1});
                }

                // 2) Ctrl + 방향키 이동
                int ctrlR = cr;
                int ctrlC = cc;

                while (true) {
                    int nextR = ctrlR + dr[i];
                    int nextC = ctrlC + dc[i];

                    if (nextR < 0 || nextR >= 4 || nextC < 0 || nextC >= 4) break;

                    ctrlR = nextR;
                    ctrlC = nextC;

                    if (board[ctrlR][ctrlC] != 0) break; // 카드를 만나면 멈춤
                }

                if (!visited[ctrlR][ctrlC]) {
                    visited[ctrlR][ctrlC] = true;
                    queue.offer(new int[]{ctrlR, ctrlC, dist + 1});
                }
            }
        }

        return 0;
    }
}