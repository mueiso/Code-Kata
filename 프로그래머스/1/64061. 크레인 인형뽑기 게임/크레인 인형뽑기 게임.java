import java.util.Stack;

class Solution {
    
    public int solution(int[][] board, int[] moves) {
        
        int answer = 0;
        Stack<Integer> basket = new Stack<>();
        
        // 1. 크레인을 moves에 지정된 위치로 이동
        for (int move : moves) {
            int col = move - 1; // 배열 인덱스는 0부터 시작하므로 -1 처리
            
            // 2. 해당 열(col)의 맨 위부터 아래로 내려가며 인형 탐색
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != 0) { // 인형을 발견한 경우
                    int doll = board[row][col];
                    board[row][col] = 0; // 인형을 집어 올렸으므로 빈칸(0)으로 변경
                    
                    // 3. 바구니(스택) 상태 확인
                    if (!basket.isEmpty() && basket.peek() == doll) {
                        // 바구니 맨 위 인형과 방금 집은 인형이 같으면 터뜨림
                        basket.pop();
                        answer += 2; // 인형 두 개가 사라짐
                    } else {
                        // 다르면 바구니에 넣음
                        basket.push(doll);
                    }
                    
                    // 인형을 하나 뽑았으므로 다음 move로 넘어감
                    break; 
                }
            }
        }
        
        return answer;
    }
}