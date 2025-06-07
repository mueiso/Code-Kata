import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (String token : s.split(" ")) {
            if (token.equals("Z")) {
                if (!stack.isEmpty()) {
                    answer -= stack.pop();
                }
            } else {
                int num = Integer.parseInt(token);
                stack.push(num);
                answer += num;
            }
        }

        return answer;
    }
}
