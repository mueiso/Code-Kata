class Solution {
    
    public int[][] solution(int[][] arr1, int[][] arr2) {

        int n = arr1.length;        // arr1의 행 수
        int m = arr1[0].length;     // arr1의 열 수 (= arr2의 행 수)
        int k = arr2[0].length;     // arr2의 열 수

        int[][] answer = new int[n][k];

        // 행렬 곱셈
        for (int i = 0; i < n; i++) {          // arr1의 행
            for (int j = 0; j < k; j++) {      // arr2의 열
                int sum = 0;
                for (int t = 0; t < m; t++) {  // 공통 차원
                    sum += arr1[i][t] * arr2[t][j];
                }
                answer[i][j] = sum;            // 결과 저장
            }
        }

        return answer;
    }
}