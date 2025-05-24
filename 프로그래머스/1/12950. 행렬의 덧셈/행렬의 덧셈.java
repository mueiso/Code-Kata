class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int rows = arr1.length;        // 행의 개수
        int cols = arr1[0].length;     // 열의 개수
        int[][] result = new int[rows][cols]; // 결과 행렬 생성
        
        // 두 행렬의 같은 위치의 원소를 더하여 결과 행렬에 저장
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        
        return result; // 결과 반환
    }
}
