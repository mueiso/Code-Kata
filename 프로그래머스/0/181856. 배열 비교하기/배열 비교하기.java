/* [문제요약]
 * 배열 길이가 다르면 → 긴 배열이 더 큼
 * 배열 길이가 같으면
   합계가 다르면 합이 큰 배열이 더 큼
   합계도 같으면 두 배열은 같음
 * arr1이 크면 1, arr2가 크면 -1, 같으면 0을 반환
 */

class Solution {
    
    public int solution(int[] arr1, int[] arr2) {
        
        // 1. 길이 비교
        if (arr1.length > arr2.length) {
            return 1;
        } else if (arr1.length < arr2.length) {
            return -1;
        }

        // 2. 길이가 같다면 합계 비교
        int sum1 = 0, sum2 = 0;
        
        for (int num : arr1) sum1 += num;
        for (int num : arr2) sum2 += num;

        if (sum1 > sum2)
            return 1;
        else if (sum1 < sum2)
            return -1;
        else return 0;
    }
}