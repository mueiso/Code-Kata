/* [문제요약]
 * 정수 배열 arr이 주어짐
 * arr.length가 2의 거듭제곱(예: 1, 2, 4, 8, 16, …)이 되도록 뒤에 0을 최소 개수로 추가
 * 결과 배열을 반환
 */

/* [문제풀이]
 * 현재 길이 len = arr.length를 구함
 * len보다 크거나 같으면서 가장 작은 2의 거듭제곱 값을 찾음 (예: nextPowerOfTwo)
 * nextPowerOfTwo - len만큼 0을 추가한 배열 생성
 */

import java.util.*;

class Solution {
    
    public int[] solution(int[] arr) {
        
        int len = arr.length;
        
        // len보다 크거나 같은 가장 작은 2의 거듭제곱 찾기
        int target = 1;
        
        while (target < len) {
            target *= 2;
        }

        // 결과 배열 생성
        int[] answer = new int[target];
        
        // arr 복사
        for (int i = 0; i < len; i++) {
            answer[i] = arr[i];
        }
        
        // 나머지 값은 기본적으로 0 (int 배열은 기본값이 0)
        return answer;
    }
}