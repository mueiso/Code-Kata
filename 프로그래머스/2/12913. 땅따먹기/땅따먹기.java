class Solution {
    
    int solution(int[][] land) {
        
        // 1. 두 번째 행(인덱스 1)부터 마지막 행까지 내려가며 누적 최댓값을 구합니다.
        for (int i = 1; i < land.length; i++) {
            // 현재 행의 각 열에 대해, 직전 행에서 자신과 다른 열의 최댓값을 더해줍니다.
            land[i][0] += Math.max(land[i-1][1], Math.max(land[i-1][2], land[i-1][3]));
            land[i][1] += Math.max(land[i-1][0], Math.max(land[i-1][2], land[i-1][3]));
            land[i][2] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][3]));
            land[i][3] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][2]));
        }

        // 2. 마지막 행까지 다 내려왔을 때, 4개의 열 중 가장 큰 점수를 찾습니다.
        int answer = 0;
        int lastRow = land.length - 1;
        for (int j = 0; j < 4; j++) {
            answer = Math.max(answer, land[lastRow][j]);
        }

        return answer;
    }
}