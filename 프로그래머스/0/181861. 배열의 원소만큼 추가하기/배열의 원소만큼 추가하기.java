import java.util.*;

class Solution {
    
    public int[] solution(int[] arr) {
        
        /*
         1. 빈 배열 X를 준비
         2. 주어진 arr을 앞에서부터 순회
         3. 원소가 a라면, X의 맨 뒤에 a를 a번 추가
         4. 최종적으로 완성된 배열 X를 반환
         */
        
        List<Integer> list = new ArrayList<>();

        // arr을 순회하면서 원소를 해당 횟수만큼 추가
        for (int a : arr) {
            for (int i = 0; i < a; i++) {
                list.add(a);
            }
        }

        // ArrayList → int[]
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
