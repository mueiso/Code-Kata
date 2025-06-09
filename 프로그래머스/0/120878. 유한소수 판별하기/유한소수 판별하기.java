class Solution {
    public int solution(int a, int b) {
        // a/b를 기약분수로 만들기 위해 최대공약수로 나눔
        int gcd = getGCD(a, b);
        b /= gcd;

        // 분모에 2와 5 이외의 소인수가 있다면 무한소수
        while (b % 2 == 0) b /= 2;
        while (b % 5 == 0) b /= 5;

        return b == 1 ? 1 : 2;
    }

    // 최대공약수(GCD)를 구하는 유클리드 호제법
    private int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}
