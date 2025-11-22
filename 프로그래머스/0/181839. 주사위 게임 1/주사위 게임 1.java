class Solution {
    
    public int solution(int a, int b) {

        boolean aOdd = (a % 2 != 0); // a가 홀수인지 여부
        boolean bOdd = (b % 2 != 0); // b가 홀수인지 여부

        // 1. a와 b가 모두 홀수인 경우
        if (aOdd && bOdd) {
            return a * a + b * b;
        }

        // 2. a와 b 중 하나만 홀수인 경우
        if (aOdd ^ bOdd) { // XOR: 하나만 true일 때 true
            return 2 * (a + b);
        }

        // 3. a와 b 모두 홀수가 아닌 경우(둘 다 짝수)
        return Math.abs(a - b);
    }
}