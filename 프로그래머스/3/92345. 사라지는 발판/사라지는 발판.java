class Solution {
    
    private static class Result {
        
        boolean win;
        int count;

        Result(boolean win, int count) {
            this.win = win;
            this.count = count;
        }
    }

    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        
        return play(board, aloc[0], aloc[1], bloc[0], bloc[1]).count;
    }

    private Result play(int[][] board, int r1, int c1, int r2, int c2) {
        
        // 1. 현재 플레이어의 발판이 사라진 경우 패배
        if (board[r1][c1] == 0) {
            return new Result(false, 0);
        }

        boolean canWin = false;
        int minWinCount = Integer.MAX_VALUE;
        int maxLoseCount = 0;
        boolean canMove = false;

        // 2. 4방향 이동 탐색
        for (int d = 0; d < 4; d++) {
            int nr = r1 + dr[d];
            int nc = c1 + dc[d];

            if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) continue;
            if (board[nr][nc] == 0) continue;

            canMove = true;

            // 백트래킹: 발판을 지우고 상대방 턴 진행
            board[r1][c1] = 0;
            Result res = play(board, r2, c2, nr, nc);
            board[r1][c1] = 1; // 복구

            // 상대가 패배했다면 현재 플레이어가 이긴 것
            if (!res.win) {
                canWin = true;
                minWinCount = Math.min(minWinCount, res.count + 1);
            } else {
                maxLoseCount = Math.max(maxLoseCount, res.count + 1);
            }
        }

        // 3. 이동할 수 있는 칸이 없으면 패배
        if (!canMove) {
            return new Result(false, 0);
        }

        // 4. 승리할 수 있으면 최소 이동 턴, 질 수밖에 없으면 최대 버틴 턴 반환
        if (canWin) {
            return new Result(true, minWinCount);
        } else {
            return new Result(false, maxLoseCount);
        }
    }
}