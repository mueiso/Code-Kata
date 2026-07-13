import java.util.ArrayDeque;

class Solution {
    
    public int solution(String s) {
        
        // 문자열을 담을 가벼운 스택 선언
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            
            // 스택이 비어있지 않고, 맨 위 문자가 현재 문자와 같다면 터트리기(제거)
            if (!stack.isEmpty() && stack.peek() == current) {
                stack.pop();
            } 
            // 짝이 맞지 않거나 스택이 비어있다면 스택에 추가
            else {
                stack.push(current);
            }
        }
        
        // 스택이 완전히 비어있으면 성공(1), 남아있으면 실패(0)
        return stack.isEmpty() ? 1 : 0;
    }
}