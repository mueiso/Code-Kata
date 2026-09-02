import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    private static class Robot {
        
        int r, c, dir, time;

        Robot(int r, int c, int dir, int time) {
            this.r = r;
            this.c = c;
            this.dir = dir; // 0: 가로, 1: 세로
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        
        int n = board.length;

        // 1. 외곽을 1(벽)로 둘러싼 패딩 맵 생성
        int[][] newBoard = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                newBoard[i][j] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }

        // 2. BFS 준비
        Queue<Robot> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n + 2][n + 2][2];

        // 초기 위치: (1, 1), 가로 방향(0), 시간 0
        queue.offer(new Robot(1, 1, 0, 0));
        visited[1][1][0] = true;

        while (!queue.isEmpty()) {
            Robot curr = queue.poll();
            int r = curr.r;
            int c = curr.c;
            int dir = curr.dir;
            int time = curr.time;

            // (N, N) 도착 여부 확인
            if ((dir == 0 && (r == n && c == n || r == n && c + 1 == n)) ||
                (dir == 1 && (r == n && c == n || r + 1 == n && c == n))) {
                return time;
            }

            // 3. 이동 및 회전 탐색
            if (dir == 0) { // 가로 방향일 때
                // 1) 평행 이동
                checkAndAdd(queue, visited, newBoard, r, c - 1, 0, time + 1, newBoard[r][c - 1] == 0);
                checkAndAdd(queue, visited, newBoard, r, c + 1, 0, time + 1, newBoard[r][c + 2] == 0);
                checkAndAdd(queue, visited, newBoard, r - 1, c, 0, time + 1, newBoard[r - 1][c] == 0 && newBoard[r - 1][c + 1] == 0);
                checkAndAdd(queue, visited, newBoard, r + 1, c, 0, time + 1, newBoard[r + 1][c] == 0 && newBoard[r + 1][c + 1] == 0);

                // 2) 회전 (위쪽 두 칸이 비어있을 때)
                if (newBoard[r - 1][c] == 0 && newBoard[r - 1][c + 1] == 0) {
                    checkAndAdd(queue, visited, newBoard, r - 1, c, 1, time + 1, true);
                    checkAndAdd(queue, visited, newBoard, r - 1, c + 1, 1, time + 1, true);
                }
                // 3) 회전 (아래쪽 두 칸이 비어있을 때)
                if (newBoard[r + 1][c] == 0 && newBoard[r + 1][c + 1] == 0) {
                    checkAndAdd(queue, visited, newBoard, r, c, 1, time + 1, true);
                    checkAndAdd(queue, visited, newBoard, r, c + 1, 1, time + 1, true);
                }
            } else { // 세로 방향일 때
                // 1) 평행 이동
                checkAndAdd(queue, visited, newBoard, r - 1, c, 1, time + 1, newBoard[r - 1][c] == 0);
                checkAndAdd(queue, visited, newBoard, r + 1, c, 1, time + 1, newBoard[r + 2][c] == 0);
                checkAndAdd(queue, visited, newBoard, r, c - 1, 1, time + 1, newBoard[r][c - 1] == 0 && newBoard[r + 1][c - 1] == 0);
                checkAndAdd(queue, visited, newBoard, r, c + 1, 1, time + 1, newBoard[r][c + 1] == 0 && newBoard[r + 1][c + 1] == 0);

                // 2) 회전 (왼쪽 두 칸이 비어있을 때)
                if (newBoard[r][c - 1] == 0 && newBoard[r + 1][c - 1] == 0) {
                    checkAndAdd(queue, visited, newBoard, r, c - 1, 0, time + 1, true);
                    checkAndAdd(queue, visited, newBoard, r + 1, c - 1, 0, time + 1, true);
                }
                // 3) 회전 (오른쪽 두 칸이 비어있을 때)
                if (newBoard[r][c + 1] == 0 && newBoard[r + 1][c + 1] == 0) {
                    checkAndAdd(queue, visited, newBoard, r, c, 0, time + 1, true);
                    checkAndAdd(queue, visited, newBoard, r + 1, c, 0, time + 1, true);
                }
            }
        }

        return 0;
    }

    private void checkAndAdd(Queue<Robot> queue, boolean[][][] visited, int[][] board, 
                             int r, int c, int dir, int time, boolean condition) {
        
        if (condition && !visited[r][c][dir]) {
            visited[r][c][dir] = true;
            queue.offer(new Robot(r, c, dir, time));
        }
    }
}