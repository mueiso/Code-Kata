class Solution {
    
    public long solution(int w, int h) {
        
        // 1. 전체 칸 수 계산 (오버플로우 방지를 위해 long 형변환)
        long totalSquares = (long) w * h;
        
        // 2. 최대공약수(GCD) 구하기
        long common = gcd(w, h);
        
        // 3. 멀쩡한 사각형 = 전체 - (W + H - GCD)
        return totalSquares - ((long) w + h - common);
    }

    /* 유클리드 호제법을 이용한 GCD 함수 */
    private long gcd(int a, int b) {
        
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        
        return a;
    }
}