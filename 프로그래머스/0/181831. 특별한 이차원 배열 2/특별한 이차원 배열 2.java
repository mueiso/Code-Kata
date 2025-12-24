class Solution {
    
    public int solution(int[][] arr) {
        
        int n = arr.length;

        // i < j 구간만 확인(대칭이면 아래도 자동으로 같아야 함)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i][j] != arr[j][i]) {
                    return 0; // 하나라도 다르면 대칭 아님
                }
            }
        }

        return 1; // 끝까지 문제 없으면 대칭
    }
}