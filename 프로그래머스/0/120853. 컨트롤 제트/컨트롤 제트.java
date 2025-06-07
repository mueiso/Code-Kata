import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        String[] arr = s.split(" ");
        
        for (String str : arr) {
            if (str.equals("Z")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }
        
        return answer;
    }
}
