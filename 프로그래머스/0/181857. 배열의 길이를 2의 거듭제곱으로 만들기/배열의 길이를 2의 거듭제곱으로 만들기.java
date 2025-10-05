/* [문제요약]

 * 정수 배열 arr이 주어짐
 * arr.length가 2의 거듭제곱(예: 1, 2, 4, 8, 16, …)이 되도록 뒤에 0을 최소 개수로 추가
 * 결과 배열을 반환
 */

/* [문제풀이]

 * 초기값
   length = 1부터 시작합니다

 * 2의 거듭제곱 계산
   while (length < arr.length)

   arr의 길이 이상이 될 때까지 length *= 2
   결과적으로 arr.length보다 크거나 같은 최소의 2의 거듭제곱이 됩니다

 * 배열 복사
   Arrays.copyOf(arr, length)

   arr의 원소를 그대로 복사하고, 남는 부분은 0으로 채움
   별도의 반복문 없이 간단하게 처리 가능
 */

import java.util.*;

class Solution {
    
    public int[] solution(int[] arr) {
        
        int length = 1;

        // arr.length 이상이 되는 가장 작은 2의 거듭제곱 구하기
        while (length < arr.length) {
            length *= 2;
        }

        // arr 복사 (남는 공간은 자동으로 0으로 채워짐)
        return Arrays.copyOf(arr, length);
    }
}