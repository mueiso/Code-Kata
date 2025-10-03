/* [요구사항]
 * 빈 배열 X에서 시작
 * 두 배열 arr, flag를 같은 인덱스로 순회
 * flag[i] == true → arr[i]를 arr[i] × 2 번 X의 뒤에 추가
 * flag[i] == false → X의 마지막 원소들 중 arr[i] 개를 제거
 * 최종적으로 X를 반환

 * 이때 크기를 알 수 없는 배열이므로, List<Integer>를 활용해 동적으로 원소를 추가/삭제한 뒤 마지막에 int[]로 변환하는 것이 깔끔
*/

import java.util.*;

class Solution {
    
    public int[] solution(int[] arr, boolean[] flag) {
        
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (flag[i]) {
                // arr[i]를 arr[i] * 2번 추가
                for (int j = 0; j < arr[i] * 2; j++) {
                    list.add(arr[i]);
                }
            } else {
                // 마지막 arr[i]개 제거
                int removeCount = arr[i];
                for (int j = 0; j < removeCount; j++) {
                    list.remove(list.size() - 1);
                }
            }
        }

        // List<Integer> → int[]
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}