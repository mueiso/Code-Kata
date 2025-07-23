import java.util.Arrays;

class Solution {
    
    public int[] solution(int[] arr, int[][] queries) {
        
        // arr 배열의 원본을 변경하지 않고 결과를 저장하기 위해 복사본을 만들어 answer에 저장 (원본 배열 arr는 그대로 유지, answer만 조작)
        int[] answer = Arrays.copyOf(arr, arr.length);

        /*
         * queries 배열을 순회하면서 각 쿼리 [i, j]에 대해:
         * answer[i]와 answer[j]의 값을 서로 바꿈(swap)
         * temp 변수를 이용한 전형적인 swap 방식
        */
        for (int[] query : queries) {
            int i = query[0];
            int j = query[1];

            int temp = answer[i];
            answer[i] = answer[j];
            answer[j] = temp;
        }

        return answer;
    }
}

/* [예제]

 * int[] arr = {10, 20, 30, 40, 50};
   int[][] queries = {{0, 3}, {1, 4}};
   
 * 1. Arrays.copyOf(arr)로 answer = {10, 20, 30, 40, 50} 생성
   2. 첫 쿼리: {0, 3} → swap answer[0]와 answer[3] → {40, 20, 30, 10, 50}
   3. 두 번째 쿼리: {1, 4} → swap answer[1]와 answer[4] → {40, 50, 30, 10, 20}
   
 * 반환값: [40, 50, 30, 10, 20]
*/