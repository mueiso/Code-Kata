import java.util.*;

public class Solution {
    
    public int[] solution(int[] arr) {

        // 결과를 담을 리스트
        List<Integer> list = new ArrayList<>();

        // 이전 값 저장용 변수
        int prev = -1;  // arr 원소는 0~9이므로 -1로 초기화

        for (int num : arr) {
            // 이전 값과 다르면 추가
            if (num != prev) {
                list.add(num);
                prev = num;
            }
        }

        // List → int[] 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}