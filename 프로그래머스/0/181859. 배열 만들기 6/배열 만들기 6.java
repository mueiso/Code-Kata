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
        
        List<Integer> stk = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (stk.isEmpty()) {
                // 규칙 1: stk이 비었으면 추가
                stk.add(arr[i]);
            } else {
                int last = stk.get(stk.size() - 1);
                if (last == arr[i]) {
                    // 규칙 2: 마지막 원소 == arr[i] → 제거
                    stk.remove(stk.size() - 1);
                } else {
                    // 규칙 3: 마지막 원소 != arr[i] → 추가
                    stk.add(arr[i]);
                }
            }
        }

        // 최종적으로 비어있으면 [-1] 반환
        if (stk.isEmpty()) {
            return new int[]{-1};
        }

        // List → int[] 변환
        int[] answer = new int[stk.size()];
        
        for (int i = 0; i < stk.size(); i++) {
            answer[i] = stk.get(i);
        }

        return answer;
    }
}