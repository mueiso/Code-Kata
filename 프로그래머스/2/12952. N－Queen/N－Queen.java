class Solution {
    
    private int answer = 0;
    private int[] board;
    private int size;

    public int solution(int n) {
        
        board = new int[n];
        size = n;
        
        // 0번 행부터 퀸 배치를 시작합니다.
        backtrack(0);
        
        return answer;
    }

    private void backtrack(int row) {
        
        // 기저 조건: 모든 행에 퀸을 충돌 없이 배치 완료한 경우
        if (row == size) {
            answer++;
            
            return;
        }

        // 현재 행(row)의 0번 열부터 N-1번 열까지 퀸을 놓을 수 있는지 시도
        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                board[row] = col;      // 퀸 배치
                backtrack(row + 1);    // 다음 행으로 전진
                // 백트래킹 특성상 다음 루프에서 board[row]의 값은 덮어씌워지므로 
                // 별도로 값을 지워줄 필요는 없습니다.
            }
        }
    }

    private boolean isSafe(int row, int col) {
        
        // 기존에 놓았던 이전 행(i)들의 퀸 위치와 비교
        for (int i = 0; i < row; i++) {
            // 1. 같은 열에 퀸이 있는지 확인
            if (board[i] == col) {
                
                return false;
            }
            // 2. 대각선에 퀸이 있는지 확인 (행의 차이와 열의 차이가 같으면 대각선)
            if (Math.abs(i - row) == Math.abs(board[i] - col)) {
                
                return false;
            }
        }
        
        return true;
    }
}