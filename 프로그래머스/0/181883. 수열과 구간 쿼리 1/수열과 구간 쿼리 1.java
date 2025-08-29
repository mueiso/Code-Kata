class Solution {
    
    public int[] solution(int[] arr, int[][] queries) {
        
        // queries의 각 쿼리를 순서대로 처리
        for (int[] query : queries) {
            
            int s = query[0]; // 시작 인덱스
            int e = query[1]; // 끝 인덱스
            
            // s ≤ i ≤ e 범위에 대해 arr[i]에 1을 더함
            for (int i = s; i <= e; i++) {
                arr[i]++;
            }
        }
        
        // 모든 쿼리 처리가 끝난 후 arr를 반환
        return arr;
    }
}
