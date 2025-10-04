/* [문제 요약]
 * arr는 0과 1로 이루어진 배열
 * stk를 빈 배열로 시작해서, arr의 원소를 순서대로 보면서 규칙에 따라 stk를 만든다:
   * stk이 비어있으면 arr[i] 추가
   * stk의 마지막 원소 == arr[i] → stk의 마지막 원소 제거
   * stk의 마지막 원소 != arr[i] → arr[i]를 stk에 추가
 * 마지막에 stk가 비어있으면 [-1]을 반환
 */

import java.util.*;

class Solution {
    
    public int[] solution(int[] arr) {
        
        Stack<Integer> stack = new Stack<>();

        for (int value : arr) {
            if (stack.isEmpty()) {
                // 규칙 1: 비어 있으면 push
                stack.push(value);
            } else {
                // 규칙 2: 마지막 값과 비교
                if (stack.peek() == value) {
                    stack.pop(); // 같으면 제거
                } else {
                    stack.push(value); // 다르면 추가
                }
            }
        }

        // 최종적으로 비어 있으면 [-1] 반환
        if (stack.isEmpty()) {
            return new int[]{-1};
        }

        // Stack → int[]
        int[] answer = new int[stack.size()];
        
        for (int i = 0; i < stack.size(); i++) {
            answer[i] = stack.get(i);
        }

        return answer;
    }
}