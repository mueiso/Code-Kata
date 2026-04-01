/*
 * 짝수층(0,2,4...) → 왼 → 오른쪽
 * 홀수층(1,3,5...) → 오 → 왼쪽
 * 즉, 행(row)에 따라 열(col)이 뒤집힘
 */

class Solution {
    
    public int solution(int n, int w, int num) {

        // 1. num의 위치 구하기
        int targetRow = (num - 1) / w;
        int targetCol = (num - 1) % w;

        // 홀수 행이면 방향 반전
        if (targetRow % 2 == 1) {
            targetCol = w - 1 - targetCol;
        }

        int answer = 1; // 자기 자신 포함

        // 2. num보다 위에 있는 박스들 확인
        for (int i = num + 1; i <= n; i++) {

            int row = (i - 1) / w;
            int col = (i - 1) % w;

            // 홀수 행이면 방향 반전
            if (row % 2 == 1) {
                col = w - 1 - col;
            }

            // 같은 column이면 위에 쌓인 박스
            if (col == targetCol) {
                answer++;
            }
        }

        return answer;
    }
}