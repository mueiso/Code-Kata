class Solution {
    public int solution(int n) {
        int sqrt = (int)Math.sqrt(n);   // n의 제곱근을 구하고 정수로 변환
        if (sqrt * sqrt == n) {
            return 1;   // 제곱수인 경우
        } else {
            return 2;   // 제곱수가 아닌 경우
        }
    }
}
