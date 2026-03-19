class Solution {
    
    public int solution(String[] board) {
        
        int oCount = 0;
        int xCount = 0;
        
        // 1. O, X 개수 세기
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') oCount++;
                if (c == 'X') xCount++;
            }
        }
        
        // 2. 개수 조건 체크
        if (xCount > oCount || oCount > xCount + 1) {
            return 0;
        }
        
        // 3. 승리 여부 확인
        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');
        
        // 4. 둘 다 승리 → 불가능
        if (oWin && xWin) return 0;
        
        // 5. O 승리인데 개수 이상하면 X
        if (oWin && oCount != xCount + 1) return 0;
        
        // 6. X 승리인데 개수 이상하면 X
        if (xWin && oCount != xCount) return 0;
        
        return 1;
    }
    
    // 특정 플레이어가 승리했는지 체크
    private boolean isWin(String[] board, char c) {
        
        // 가로 검사
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c &&
                board[i].charAt(1) == c &&
                board[i].charAt(2) == c) {
                return true;
            }
        }
        
        // 세로 검사
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == c &&
                board[1].charAt(j) == c &&
                board[2].charAt(j) == c) {
                return true;
            }
        }
        
        // 대각선 검사
        if (board[0].charAt(0) == c &&
            board[1].charAt(1) == c &&
            board[2].charAt(2) == c) {
            return true;
        }
        
        if (board[0].charAt(2) == c &&
            board[1].charAt(1) == c &&
            board[2].charAt(0) == c) {
            return true;
        }
        
        return false;
    }
}