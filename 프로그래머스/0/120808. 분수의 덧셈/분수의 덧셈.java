class Solution {
    
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {

        // 1. 분수 덧셈
        int numer = numer1 * denom2 + numer2 * denom1; // 통분 후 분자
        int denom = denom1 * denom2;                   // 통분 후 분모

        // 2. 최대공약수 구하기
        int gcd = gcd(numer, denom);

        // 3. 기약분수로 만들기
        numer /= gcd;
        denom /= gcd;

        return new int[]{numer, denom};
    }

    // 유클리드 호제법
    private int gcd(int a, int b) {
        
        if (b == 0) return a;
        
        return gcd(b, a % b);
    }
}