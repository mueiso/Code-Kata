/*
 * 스택에는 항상 가능한 한 큰 수가 앞에 오도록 유지
 * peek < 현재 숫자일 때만 제거 → 무조건 pop ❌
 * 끝까지 돌았는데 k 남으면 뒤에서 제거
 * 문자열 → char 단위 처리 (숫자 변환 불필요)
 */

import java.util.*;

class Solution {
    
    public String solution(String number, int k) {
        
        Stack<Character> stack = new Stack<>();

        for (char c : number.toCharArray()) {
            // 현재 숫자가 더 크면 이전 작은 숫자 제거
            while (!stack.isEmpty() && k > 0 && stack.peek() < c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // 아직 제거하지 못한 수가 남아 있으면 뒤에서 제거
        while (k > 0) {
            stack.pop();
            k--;
        }

        // 스택에 남은 숫자를 문자열로 변환
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }
}