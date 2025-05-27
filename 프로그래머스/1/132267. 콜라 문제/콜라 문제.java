class Solution {
    public int solution(int a, int b, int n) {
        int total = 0;

        while (n >= a) {
            int newCoke = (n / a) * b;
            total += newCoke;
            n = (n % a) + newCoke;
        }

        return total;
    }
}