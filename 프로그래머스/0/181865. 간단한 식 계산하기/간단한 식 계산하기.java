class Solution {
    
    public int solution(String binomial) {
        
        /*
         * 주어진 조건을 정리하면:
         * binomial은 "a op b" 형태의 문자열
         * a, b → 음이 아닌 정수
         * op → +, -, * 중 하나
         * 이를 계산한 결과를 정수로 반환해야 함
         
         * 즉, 문자열을 공백 기준으로 잘라서 연산만 해주면 된다
         */
        
        // "a op b" 를 공백으로 split
        String[] parts = binomial.split(" ");
        
        int a = Integer.parseInt(parts[0]); // 왼쪽 피연산자
        String op = parts[1];               // 연산자
        int b = Integer.parseInt(parts[2]); // 오른쪽 피연산자
        
        // 연산자에 따라 계산
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                throw new IllegalArgumentException("잘못된 연산자: " + op);
        }
    }
}
