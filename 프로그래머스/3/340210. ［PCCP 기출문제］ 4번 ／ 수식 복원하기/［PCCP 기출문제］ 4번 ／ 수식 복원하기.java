import java.util.*;

class Solution {
    
    public String[] solution(String[] expressions) {
        
        int maxDigit = 0;
        List<String> completeExps = new ArrayList<>();
        List<String> incompleteExps = new ArrayList<>();
        
        // 1. 수식 분류 및 등장한 가장 큰 숫자(Max Digit) 찾기
        for (String exp : expressions) {
            for (char c : exp.toCharArray()) {
                if (Character.isDigit(c)) {
                    maxDigit = Math.max(maxDigit, c - '0');
                }
            }
            if (exp.endsWith("X")) {
                incompleteExps.add(exp);
            } else {
                completeExps.add(exp);
            }
        }
        
        // 2. 가능한 진법 후보 초기화 (등장한 가장 큰 숫자 + 1 부터 9진법까지)
        Set<Integer> validBases = new HashSet<>();
        for (int i = Math.max(2, maxDigit + 1); i <= 9; i++) {
            validBases.add(i);
        }
        
        // 3. 온전한 수식을 돌며 모순되는 진법 제거
        for (String exp : completeExps) {
            String[] parts = exp.split(" ");
            String A = parts[0];
            String op = parts[1];
            String B = parts[2];
            String C = parts[4];
            
            Iterator<Integer> it = validBases.iterator();
            while (it.hasNext()) {
                int base = it.next();
                // 문자열을 base 진법으로 읽어 10진수로 변환
                int valA = Integer.parseInt(A, base);
                int valB = Integer.parseInt(B, base);
                int valC = Integer.parseInt(C, base);
                
                boolean isValid = false;
                if (op.equals("+")) {
                    isValid = (valA + valB == valC);
                } else if (op.equals("-")) {
                    isValid = (valA - valB == valC);
                }
                
                // 계산이 틀리다면 해당 진법은 거짓이므로 제거
                if (!isValid) {
                    it.remove();
                }
            }
        }
        
        // 4. 불완전한 수식('X') 계산 및 정답 배열 생성
        String[] answer = new String[incompleteExps.size()];
        for (int i = 0; i < incompleteExps.size(); i++) {
            String exp = incompleteExps.get(i);
            String[] parts = exp.split(" ");
            String A = parts[0];
            String op = parts[1];
            String B = parts[2];
            
            Set<String> possibleResults = new HashSet<>();
            
            for (int base : validBases) {
                int valA = Integer.parseInt(A, base);
                int valB = Integer.parseInt(B, base);
                
                int decimalResult = op.equals("+") ? (valA + valB) : (valA - valB);
                // 10진수 결과를 다시 해당 진법의 문자열로 변환
                possibleResults.add(Integer.toString(decimalResult, base));
            }
            
            // 결과가 오직 하나로 특정된다면 그 값을, 여러 개라면 '?'를 대입
            if (possibleResults.size() == 1) {
                answer[i] = A + " " + op + " " + B + " = " + possibleResults.iterator().next();
            } else {
                answer[i] = A + " " + op + " " + B + " = ?";
            }
        }
        
        return answer;
    }
}