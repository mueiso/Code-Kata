class Solution {
    
    public String solution(String p) {
        
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (p.isEmpty()) {
            return "";
        }

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        int count = 0;
        int splitIdx = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            
            // 처음으로 균형이 맞추어지는 순간 split
            if (count == 0) {
                splitIdx = i;
                break;
            }
        }

        String u = p.substring(0, splitIdx + 1);
        String v = p.substring(splitIdx + 1);

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면
        if (isCorrect(u)) {
            // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            return u + solution(v);
        } else {
            // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면
            StringBuilder sb = new StringBuilder();
            sb.append("(");             // 4-1
            sb.append(solution(v));     // 4-2
            sb.append(")");             // 4-3

            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 붙입니다.
            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') {
                    sb.append(")");
                } else {
                    sb.append("(");
                }
            }

            // 4-5. 생성된 문자열을 반환합니다.
            return sb.toString();
        }
    }

    /* 올바른 괄호 문자열인지 확인하는 함수 */
    private boolean isCorrect(String s) {
        
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            // 음수가 되는 순간 올바르지 않은 괄호
            if (count < 0) {
                return false;
            }
        }
        
        return count == 0;
    }
}