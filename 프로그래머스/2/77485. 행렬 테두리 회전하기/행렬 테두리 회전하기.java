/*
 * 초기 행렬 생성
   rows x columns
   (0,0)부터 1 ~ rows*columns 순서대로 채움

 * 각 query 처리
   (x1, y1, x2, y2)는 1-indexed → 코드에서는 0-indexed로 변환

 * 테두리 회전 방식
   왼쪽 위 값을 임시 저장
   아래 → 오른쪽 → 위 → 왼쪽 순으로 값 이동
   이동하면서 최솟값 갱신
   
 * 각 회전마다
   회전에 참여한 숫자 중 최솟값을 answer에 저장
 */

class Solution {
    
    public int[] solution(int rows, int columns, int[][] queries) {
        
        int[][] matrix = new int[rows][columns];
        int num = 1;

        // 1. 행렬 초기화
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = num++;
            }
        }

        int[] answer = new int[queries.length];

        // 2. 각 회전 처리
        for (int q = 0; q < queries.length; q++) {
            int x1 = queries[q][0] - 1;
            int y1 = queries[q][1] - 1;
            int x2 = queries[q][2] - 1;
            int y2 = queries[q][3] - 1;

            int temp = matrix[x1][y1]; // 시작 값 저장
            int min = temp;

            // 왼쪽 열 ↑
            for (int i = x1; i < x2; i++) {
                matrix[i][y1] = matrix[i + 1][y1];
                min = Math.min(min, matrix[i][y1]);
            }

            // 아래 행 ←
            for (int j = y1; j < y2; j++) {
                matrix[x2][j] = matrix[x2][j + 1];
                min = Math.min(min, matrix[x2][j]);
            }

            // 오른쪽 열 ↓
            for (int i = x2; i > x1; i--) {
                matrix[i][y2] = matrix[i - 1][y2];
                min = Math.min(min, matrix[i][y2]);
            }

            // 위 행 →
            for (int j = y2; j > y1 + 1; j--) {
                matrix[x1][j] = matrix[x1][j - 1];
                min = Math.min(min, matrix[x1][j]);
            }

            matrix[x1][y1 + 1] = temp; // 저장한 값 복원
            answer[q] = min;
        }

        return answer;
    }
}