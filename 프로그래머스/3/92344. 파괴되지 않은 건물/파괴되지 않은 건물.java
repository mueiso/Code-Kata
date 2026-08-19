class Solution {
    
    public int solution(int[][] board, int[][] skill) {
        
        int n = board.length;
        int m = board[0].length;
        
        // r2+1, c2+1 인덱스 접근을 위해 (N+1) x (M+1) 크기 배열 생성
        int[][] sum = new int[n + 1][m + 1];

        // 1. 모든 스킬 변화량을 O(1)에 마킹 (O(K))
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = (type == 1) ? -s[5] : s[5];

            sum[r1][c1] += degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
            sum[r2 + 1][c2 + 1] += degree;
        }

        // 2. 가로 방향 누적합 계산 (좌 -> 우)
        for (int r = 0; r < n; r++) {
            for (int c = 1; c < m; c++) {
                sum[r][c] += sum[r][c - 1];
            }
        }

        // 3. 세로 방향 누적합 계산 (상 -> 하)
        for (int r = 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                sum[r][c] += sum[r - 1][c];
            }
        }

        // 4. 원본 board에 반영 후 내구도가 1 이상인 건물 카운트 (O(N * M))
        int answer = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] + sum[r][c] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}