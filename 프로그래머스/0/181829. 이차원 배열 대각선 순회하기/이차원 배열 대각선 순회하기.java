class Solution {
    
    public int solution(int[][] board, int k) {
        
        int answer = 0;

        // 모든 좌표 (i, j)를 순회
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                // 인덱스 합이 k 이하인 경우만 더함
                if (i + j <= k) {
                    answer += board[i][j];
                }
            }
        }

        return answer;
    }
}