class Solution {
    
    // queries[i] = [s, e, k] 이고, 인덱스 j가 s <= j <= e 범위이며 j % k == 0이면 arr[j] += 1 수행
    public int[] solution(int[] arr, int[][] queries) {
        
        // i는 현재 쿼리의 인덱스
        for (int i = 0; i < queries.length; i++) {
            // j는 현재 쿼리의 시작 인덱스 s부터 종료 인덱스 e까지 반복
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                // j가 k의 배수이면 arr[j] 값을 1 증가시킴
                if (j % queries[i][2] == 0) arr[j] += 1;
            }
        }
        
        return arr;
    }
}