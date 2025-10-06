/* [문제요약]
 * 배열 길이가 다르면 → 긴 배열이 더 큼
 * 배열 길이가 같으면
   합계가 다르면 합이 큰 배열이 더 큼
   합계도 같으면 두 배열은 같음
 * arr1이 크면 1, arr2가 크면 -1, 같으면 0을 반환
 */

import java.util.stream.IntStream;

class Solution {
    
    public int solution(int[] arr1, int[] arr2) {
        
        /*
         1. 배열 길이 비교
         * arr1.length > arr2.length → 1 
         * arr1.length < arr2.length → -1
         * 같으면 0
         */
        int answer = Integer.compare(arr1.length, arr2.length);

        // 2. 길이가 같을 경우 원소 합으로 비교
        if (answer == 0) {
            // IntStream.of(arr).sum() → 배열 원소 합 계산
            answer = Integer.compare(IntStream.of(arr1).sum(), IntStream.of(arr2).sum());
        }

        // 3. arr1이 크면 1, arr2가 크면 -1, 같으면 0
        return answer;
    }
}