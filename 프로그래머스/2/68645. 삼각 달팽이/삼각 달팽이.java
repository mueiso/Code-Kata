/*
 * 방향은 3개 고정
   - 아래
   - 오른쪽
   - 왼쪽 위 대각선
 * i % 3으로 방향 제어
 * 삼각형이므로 j <= i까지만 결과에 포함
 * 좌표 실수 방지 위해 초기값 x = -1 세팅
 */

class Solution {
    
    public int[] solution(int n) {
        
        int[][] triangle = new int[n][n];
        int num = 1;

        int x = -1; // 행
        int y = 0;  // 열

        // i는 방향 전환 횟수
        for (int i = 0; i < n; i++) {
            // 각 단계마다 이동 횟수는 n - i
            for (int j = i; j < n; j++) {
                // 아래로 이동
                if (i % 3 == 0) {
                    x++;
                }
                // 오른쪽으로 이동
                else if (i % 3 == 1) {
                    y++;
                }
                // 왼쪽 위 대각선 이동
                else {
                    x--;
                    y--;
                }
                triangle[x][y] = num++;
            }
        }

        // 결과를 1차원 배열로 변환
        int[] answer = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = triangle[i][j];
            }
        }

        return answer;
    }
}