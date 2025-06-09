class Solution {
    public int solution(int a, int b) {
        int gcd = gcd(a, b);
        int denom = b / gcd;

        while (denom % 2 == 0) denom /= 2;
        while (denom % 5 == 0) denom /= 5;

        return (denom == 1) ? 1 : 2;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
