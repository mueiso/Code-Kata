class Solution {
    public long solution(long n) {
        // 제곱근을 구하고, 그 제곱이 n과 같은지 확인
        double sqrt = Math.sqrt(n);
        
        // sqrt가 정수라면 (제곱수라면)
        if (sqrt == (int) sqrt) {
            // x+1의 제곱을 반환
            long x = (long) sqrt;
            return (x + 1) * (x + 1);
        } else {
            // 제곱수가 아니면 -1을 반환
            return -1;
        }
    }
}
