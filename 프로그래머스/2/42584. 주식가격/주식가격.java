import java.util.Stack;

class Solution {
    
    public int[] solution(int[] prices) {
        
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>(); // 주식 가격의 인덱스를 저장할 스택

        for (int i = 0; i < prices.length; i++) {
            // 스택이 비어있지 않고, 현재 가격이 스택 맨 위 인덱스의 가격보다 떨어졌다면
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx; // 가격이 떨어질 때까지 걸린 시간 저장
            }
            stack.push(i); // 현재 인덱스를 스택에 삽입
        }

        // 반복문이 끝난 후에도 스택에 남아있는 인덱스 처리 (끝까지 가격이 떨어지지 않은 경우)
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = prices.length - 1 - idx; // 전체 시간에서 자신의 위치를 뺌
        }

        return answer;
    }
}