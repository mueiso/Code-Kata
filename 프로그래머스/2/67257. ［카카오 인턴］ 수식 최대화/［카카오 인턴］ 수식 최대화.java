import java.util.*;

class Solution {
    
    // 모든 연산자 우선순위 조합 (3! = 6가지)
    private static final String[][] priorities = {
        
        {"+", "-", "*"}, {"+", "*", "-"},
        {"-", "+", "*"}, {"-", "*", "+"},
        {"*", "+", "-"}, {"*", "-", "+"}
    };

    public long solution(String expression) {
        
        long answer = 0;

        // 1. 숫자와 연산자를 리스트로 분리
        List<Long> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        
        // 숫자 파싱 (연산자를 구분자로 사용)
        String[] numArr = expression.split("[\\+\\-\\*]");
        for (String n : numArr) numbers.add(Long.parseLong(n));
        
        // 연산자 파싱
        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') operators.add(String.valueOf(c));
        }

        // 2. 6가지 우선순위 조합에 대해 시뮬레이션 진행
        for (String[] p : priorities) {
            // 원본 리스트 복사하여 사용
            List<Long> subNums = new ArrayList<>(numbers);
            List<String> subOps = new ArrayList<>(operators);

            for (String targetOp : p) {
                for (int i = 0; i < subOps.size(); ) {
                    if (subOps.get(i).equals(targetOp)) {
                        // 연산 수행
                        long res = calc(subNums.get(i), subNums.get(i + 1), targetOp);
                        
                        // 사용된 숫자와 연산자 제거 및 결과 삽입
                        subNums.remove(i);
                        subNums.remove(i);
                        subNums.add(i, res);
                        subOps.remove(i);
                        // 연산자를 제거했으므로 인덱스 i는 증가시키지 않음
                    } else {
                        i++;
                    }
                }
            }
            // 절댓값 중 최댓값 갱신
            answer = Math.max(answer, Math.abs(subNums.get(0)));
        }

        return answer;
    }

    private long calc(long a, long b, String op) {
        
        if (op.equals("+")) return a + b;
        if (op.equals("-")) return a - b;
        
        return a * b;
    }
}