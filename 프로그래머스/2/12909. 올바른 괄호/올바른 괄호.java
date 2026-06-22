class Solution {
    
    boolean solution(String s) {
        
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }

            // 중간에 닫힌 괄호가 더 많아지면 즉시 올바르지 않은 괄호로 판단
            if (count < 0) {
                return false;
            }
        }

        // 끝났을 때 딱 0으로 떨어져야 올바른 괄호입니다.
        return count == 0;
    }
}