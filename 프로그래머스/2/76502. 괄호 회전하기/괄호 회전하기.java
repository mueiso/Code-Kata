import java.util.*;

class Solution {
    
    public int solution(String s) {
        
        int n = s.length();
        int answer = 0;

        // x칸 왼쪽 회전: s[x..끝] + s[0..x-1]
        for (int x = 0; x < n; x++) {
            String rotated = s.substring(x) + s.substring(0, x);

            // 회전한 문자열이 올바른 괄호 문자열이면 카운트
            if (isValid(rotated)) {
                answer++;
            }
        }

        return answer;
    }

    // 스택으로 올바른 괄호 문자열인지 검사
    private boolean isValid(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            // 여는 괄호면 스택에 push
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 닫는 괄호인데 스택이 비면 실패
                if (stack.isEmpty()) return false;

                char top = stack.pop();

                // 짝이 안 맞으면 실패
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        // 끝까지 처리 후 스택이 비어야 올바름
        return stack.isEmpty();
    }
}