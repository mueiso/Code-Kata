class Solution {
    
    int N;
    int minRotations = Integer.MAX_VALUE;
    // 제자리, 상, 하, 좌, 우
    int[] dr = {0, -1, 1, 0, 0};
    int[] dc = {0, 0, 0, -1, 1};

    public int solution(int[][] clockHands) {
        
        N = clockHands.length;
        int[] firstRowRot = new int[N]; // 첫 번째 줄의 각 열별 회전 횟수 (0~3)
        
        // 1. 첫 번째 줄의 모든 회전 경우의 수 탐색 (DFS)
        dfs(0, firstRowRot, clockHands);
        
        return minRotations;
    }

    private void dfs(int col, int[] firstRowRot, int[][] clockHands) {
        
        if (col == N) {
            // 첫 번째 줄의 조작이 결정되었으므로 시뮬레이션 시작
            simulate(firstRowRot, clockHands);
            
            return;
        }

        // 각 시계는 0번, 1번, 2번, 3번 회전할 수 있음
        for (int i = 0; i < 4; i++) {
            firstRowRot[col] = i;
            dfs(col + 1, firstRowRot, clockHands);
        }
    }

    private void simulate(int[] firstRowRot, int[][] clockHands) {
        
        // 원본 배열을 훼손하지 않기 위해 복사
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = clockHands[i].clone();
        }

        int totalRot = 0;

        // 1. 첫 번째 행(Row 0)에 결정된 회전 적용
        for (int c = 0; c < N; c++) {
            if (firstRowRot[c] > 0) {
                rotate(board, 0, c, firstRowRot[c]);
                totalRot += firstRowRot[c];
            }
        }

        // 2. 두 번째 행(Row 1)부터 아래로 내려가며 윗줄을 12시로 맞춤
        for (int r = 1; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 바로 위칸(r-1)의 시계를 0으로 만들기 위해 필요한 현재 칸(r)의 회전 수
                int rot = (4 - board[r - 1][c]) % 4;
                if (rot > 0) {
                    rotate(board, r, c, rot);
                    totalRot += rot;
                }
            }
        }

        // 3. 마지막 행(Row N-1)이 모두 12시(0)인지 검증
        boolean isSolved = true;
        for (int c = 0; c < N; c++) {
            if (board[N - 1][c] != 0) {
                isSolved = false;
                
                break;
            }
        }

        // 성공했다면 최소 조작 횟수 갱신
        if (isSolved) {
            minRotations = Math.min(minRotations, totalRot);
        }
    }

    /* (r, c) 시계를 rot만큼 회전시킬 때 십자가 모양으로 적용하는 함수 */
    private void rotate(int[][] board, int r, int c, int rot) {
        
        for (int i = 0; i < 5; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            // 경계 밖을 벗어나지 않는다면 회전 적용
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                // 기존 값에 회전수를 더하고 4로 나눈 나머지 (0, 1, 2, 3 유지)
                board[nr][nc] = (board[nr][nc] + rot) % 4;
            }
        }
    }
}