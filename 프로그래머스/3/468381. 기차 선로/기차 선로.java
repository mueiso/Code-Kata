import java.util.*;

class Solution {
    
    private int n, m;
    private int[][] originalGrid;
    private int[][] board;
    private int answerCount;

    /* 선로 종류별 연결 방향 비트마스크 (UP=1, RIGHT=2, DOWN=4, LEFT=8)
     0: 빈칸(0)
     1: Horizontal (2 | 8 = 10)
     2: Vertical (1 | 4 = 5)
     3: Cross (1 | 2 | 4 | 8 = 15)
     4: UP | LEFT (1 | 8 = 9)
     5: UP | RIGHT (1 | 2 = 3)
     6: DOWN | RIGHT (4 | 2 = 6)
     7: DOWN | LEFT (4 | 8 = 12)
    */
    private static final int[] TRACK_MASK = {0, 10, 5, 15, 9, 3, 6, 12};

    // 방향 이동 벡터: 0: UP, 1: RIGHT, 2: DOWN, 3: LEFT
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    public int solution(int[][] grid) {
        
        this.n = grid.length;
        this.m = grid[0].length;
        this.originalGrid = grid;
        this.board = new int[n][m];
        this.answerCount = 0;

        dfs(0);

        return answerCount;
    }

    private void dfs(int cellIdx) {
        
        if (cellIdx == n * m) {
            if (validateBoard()) {
                answerCount++;
            }
            return;
        }

        int r = cellIdx / m;
        int c = cellIdx % m;

        // 위쪽 및 왼쪽 칸과의 연결 요구사항 판단
        boolean reqUp = false;
        if (r > 0) {
            reqUp = (TRACK_MASK[board[r - 1][c]] & 4) != 0; // 위쪽 칸에 아래 방향 연결 존재 여부
        }

        boolean reqLeft = false;
        if (c > 0) {
            reqLeft = (TRACK_MASK[board[r][c - 1]] & 2) != 0; // 왼쪽 칸에 오른쪽 방향 연결 존재 여부
        } else if (r == 0 && c == 0) {
            reqLeft = true; // 출발점 (0,0)은 진입을 위해 왼쪽 연결 필요
        }

        int fixed = originalGrid[r][c];

        for (int type = 0; type <= 7; type++) {
            // 고정 입력 조건 검사
            if (fixed == -1 && type != 0) continue; // 장애물 칸은 빈칸이어야 함
            if (fixed > 0 && type != fixed) continue; // 이미 배치된 선로는 변경 불가

            int mask = TRACK_MASK[type];

            // 위쪽, 왼쪽 연결 요구 조건 검사
            boolean hasUp = (mask & 1) != 0;
            if (hasUp != reqUp) continue;

            boolean hasLeft = (mask & 8) != 0;
            if (hasLeft != reqLeft) continue;

            // 아래쪽 경계 조건 검사
            boolean hasDown = (mask & 4) != 0;
            if (r == n - 1) {
                if (r == n - 1 && c == m - 1) {
                    boolean destReqDown = (originalGrid[n - 1][m - 1] == 2);
                    if (hasDown != destReqDown) continue;
                } else {
                    if (hasDown) continue; // 아래쪽 경계 밖 연결 불가
                }
            }

            // 오른쪽 경계 조건 검사
            boolean hasRight = (mask & 2) != 0;
            if (c == m - 1) {
                if (r == n - 1 && c == m - 1) {
                    boolean destReqRight = (originalGrid[n - 1][m - 1] == 1);
                    if (hasRight != destReqRight) continue;
                } else {
                    if (hasRight) continue; // 오른쪽 경계 밖 연결 불가
                }
            }

            board[r][c] = type;
            dfs(cellIdx + 1);
            board[r][c] = 0;
        }
    }

    private boolean validateBoard() {
        
        int totalTracks = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] > 0) {
                    totalTracks++;
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        int visitedCount = 0;

        int r = 0, c = 0;
        int dir = 1; // (0,0)에서 오른쪽 방향으로 시작

        int steps = 0;
        while (steps < 200) {
            steps++;

            if (!visited[r][c]) {
                visited[r][c] = true;
                visitedCount++;
            }

            int type = board[r][c];
            int exitDir = getNextDir(type, dir);
            if (exitDir == -1) return false;

            // 목적지 도착 및 격자 이탈 확인
            if (r == n - 1 && c == m - 1) {
                if ((type == 1 && exitDir == 1) || (type == 2 && exitDir == 2)) {
                    return visitedCount == totalTracks;
                }
            }

            int nextR = r + DR[exitDir];
            int nextC = c + DC[exitDir];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                return false;
            }

            if (board[nextR][nextC] == 0) {
                return false;
            }

            r = nextR;
            c = nextC;
            dir = exitDir;
        }

        return false;
    }

    private int getNextDir(int type, int inDir) {
        
        // inDir: 기차가 칸에 진입할 때의 진행 방향 (0: UP, 1: RIGHT, 2: DOWN, 3: LEFT)
        switch (type) {
            case 1: // 직선(가로)
                if (inDir == 1) return 1;
                if (inDir == 3) return 3;
                return -1;
            case 2: // 직선(세로)
                if (inDir == 2) return 2;
                if (inDir == 0) return 0;
                return -1;
            case 3: // 십자(#)
                return inDir;
            case 4: // 곡선 (UP, LEFT)
                if (inDir == 1) return 0; // 왼쪽에서 진입 -> 위로 통과
                if (inDir == 2) return 3; // 위에서 진입 -> 왼쪽으로 통과
                return -1;
            case 5: // 곡선 (UP, RIGHT)
                if (inDir == 2) return 1; // 위에서 진입 -> 오른쪽으로 통과
                if (inDir == 3) return 0; // 오른쪽에서 진입 -> 위로 통과
                return -1;
            case 6: // 곡선 (DOWN, RIGHT)
                if (inDir == 0) return 1; // 아래에서 진입 -> 오른쪽으로 통과
                if (inDir == 3) return 2; // 오른쪽에서 진입 -> 아래로 통과
                return -1;
            case 7: // 곡선 (DOWN, LEFT)
                if (inDir == 0) return 3; // 아래에서 진입 -> 왼쪽으로 통과
                if (inDir == 1) return 2; // 왼쪽에서 진입 -> 아래로 통과
                return -1;
            default:
                return -1;
        }
    }
}