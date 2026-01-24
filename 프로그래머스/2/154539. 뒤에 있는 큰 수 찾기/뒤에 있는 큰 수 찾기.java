import java.util.*;

class Solution {
    
    public int[] solution(int[] numbers) {
        
        int n = numbers.length;
        int[] answer = new int[n];
        
        // 기본값을 -1로 설정 (뒷 큰수가 없는 경우)
        Arrays.fill(answer, -1);

        // 뒷 큰수를 아직 찾지 못한 인덱스를 저장하는 스택
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 현재 숫자가 스택 top이 가리키는 숫자보다 크면
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                int idx = stack.pop();
                answer[idx] = numbers[i]; // 현재 값이 뒷 큰수
            }
            // 현재 인덱스를 스택에 저장
            stack.push(i);
        }

        return answer;
    }
}