/*
 * 새로운 boolean 배열 danger 생성
 * 지뢰를 발견하면 주변 8칸 모두 true 처리
 * 마지막에 danger == false 인 칸 개수 세기
 */

class Solution {
    
    public int solution(int[][] board) {
        
        int n = board.length;
        boolean[][] danger = new boolean[n][n]; // 위험 지역 표시용

        // 8방향 이동 배열 (자기 자신 포함 9칸)
        int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

        // 1. 지뢰 위치 기준으로 위험 지역 표시
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) { // 지뢰 발견
                    for (int d = 0; d < 9; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        // 범위 안이면 위험 표시
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                            danger[nx][ny] = true;
                        }
                    }
                }
            }
        }

        int answer = 0;

        // 2. 안전 지역(위험 표시 안 된 곳) 개수 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!danger[i][j]) {
                    answer++;
                }
            }
        }

        return answer;
    }
}