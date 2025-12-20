class Solution {
    
    public int[][] solution(int n) {
        
        int[][] answer = new int[n][n];

        int num = 1;        // 1부터 채울 숫자
        int start = 0;      // 현재 레이어의 시작 인덱스
        int end = n;        // 현재 레이어의 끝 인덱스(미포함)

        // 모든 칸이 채워질 때까지 반복
        while (num <= n * n) {

            // → 위쪽 가로 채우기
            for (int j = start; j < end; j++)
                answer[start][j] = num++;

            // ↓ 오른쪽 세로 채우기
            for (int i = start + 1; i < end; i++)
                answer[i][end - 1] = num++;

            // ← 아래쪽 가로 채우기
            for (int j = end - 2; j >= start; j--)
                answer[end - 1][j] = num++;

            // ↑ 왼쪽 세로 채우기
            for (int i = end - 2; i > start; i--)
                answer[i][start] = num++;

            // 다음 안쪽 레이어로 이동
            start++;
            end--;
        }

        return answer;
    }
}