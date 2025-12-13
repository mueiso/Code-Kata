class Solution {
    
    public int[][] solution(int n) {
        
        int[][] answer = new int[n][n];

        // 대각선(i == j)만 1로 설정
        for (int i = 0; i < n; i++) {
            answer[i][i] = 1;
        }

        return answer;
    }
}