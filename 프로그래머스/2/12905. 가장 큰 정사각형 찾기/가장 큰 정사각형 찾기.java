class Solution {
    
    public int solution(int [][]board) {
        
        int maxSide = 0; // 가장 큰 정사각형의 한 변의 길이를 저장할 변수
        int row = board.length;
        int col = board[0].length;

        // 표를 전체적으로 한 바퀴 순회합니다.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 첫 번째 행이 아니고, 첫 번째 열이 아니며, 현재 칸이 1인 경우에만 DP 연산을 합니다.
                if (i > 0 && j > 0 && board[i][j] == 1) {
                    int up = board[i - 1][j];
                    int left = board[i][j - 1];
                    int upLeft = board[i - 1][j - 1];
                    
                    // 세 방향의 최솟값에 1을 더해 현재 위치에 업데이트합니다.
                    board[i][j] = Math.min(Math.min(up, left), upLeft) + 1;
                }
                
                // 매 칸마다 현재까지 발견한 가장 긴 변의 길이를 갱신합니다.
                maxSide = Math.max(maxSide, board[i][j]);
            }
        }

        // 문제는 '넓이'를 요구하므로 한 변의 길이의 제곱을 반환합니다.
        return maxSide * maxSide;
    }
}