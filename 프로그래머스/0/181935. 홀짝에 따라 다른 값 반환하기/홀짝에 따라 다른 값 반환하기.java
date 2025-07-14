class Solution {
    public int solution(int n) {
        
        int sum = 0;

        if (n % 2 == 1) { // 홀수인 경우
            for (int i = 1; i <= n; i += 2) {
                sum += i;
            }
        } else { // 짝수인 경우
            for (int i = 2; i <= n; i += 2) {
                sum += i * i;
            }
        }

        return sum;
    }
}
