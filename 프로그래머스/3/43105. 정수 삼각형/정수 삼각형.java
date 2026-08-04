class Solution {
    
    public int solution(int[][] triangle) {
        
        int n = triangle.length;

        // 밑에서 두 번째 줄(n-2)부터 꼭대기(0)까지 역순으로 올라감
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                // 아래 칸(j)과 아래 오른쪽 칸(j+1) 중 더 큰 값을 현재 위치에 누적
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }

        // 꼭대기에 최종 최댓값이 모이게 됨
        return triangle[0][0];
    }
}