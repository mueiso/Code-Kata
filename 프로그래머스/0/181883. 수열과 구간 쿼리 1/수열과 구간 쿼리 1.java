class Solution {
    
    public int[] solution(int[] arr, int[][] queries) {
        
        // 결과를 담을 배열(현재는 사용하지 않고 arr를 바로 반환)
        int[] answer = {};

        // queries 배열을 순회 (각 query = [s, e])
        for(int i=0; i<queries.length; i++){

            // query 범위: s부터 e까지 arr의 해당 구간 값을 +1
            for(int j=queries[i][0]; j<=queries[i][1]; j++){
                arr[j]++; // arr[j]의 값을 1 증가시킴
            }
        }

        // 모든 query 처리가 끝난 후, 수정된 arr를 반환
        return arr;
    }
}

/*
 1. queries는 [s, e] 구간을 의미 → arr[s]부터 arr[e]까지의 원소를 +1 증가시킴
 2. 모든 쿼리를 차례대로 수행하면서 arr 배열을 직접 변경
 3. 최종적으로 arr를 반환
 */