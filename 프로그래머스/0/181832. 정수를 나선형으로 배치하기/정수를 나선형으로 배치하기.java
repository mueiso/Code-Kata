class Solution {
    
    public int[][] solution(int n) {
        
        int[][] answer = new int[n][n];

        int num = 1;
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;

        while (num <= n * n) {

            // → 오른쪽
            for (int j = left; j <= right; j++) {
                answer[top][j] = num++;
            }
            top++;

            // ↓ 아래
            for (int i = top; i <= bottom; i++) {
                answer[i][right] = num++;
            }
            right--;

            // ← 왼쪽
            for (int j = right; j >= left; j--) {
                answer[bottom][j] = num++;
            }
            bottom--;

            // ↑ 위
            for (int i = bottom; i >= top; i--) {
                answer[i][left] = num++;
            }
            left++;
        }

        return answer;
    }
}