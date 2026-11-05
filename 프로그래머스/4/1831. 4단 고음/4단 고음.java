class Solution {
    
    public int solution(int n) {
        
        return dfs(n, 0);
    }

    /* n: 현재 음높이, plus: 아직 '*'와 짝지어지지 않은 '+' 개수 */
    private int dfs(long n, int plus) {
        
        if (n < 1) return 0;
        if (pow3((plus + 1) / 2) > n) return 0;   // 가지치기
        if (n == 1) return plus == 0 ? 1 : 0;

        int count = 0;
        // 마지막 문자가 '*'인 경우
        if (n % 3 == 0 && plus >= 2) {
            count += dfs(n / 3, plus - 2);
        }
        // 마지막 문자가 '+'인 경우
        count += dfs(n - 1, plus + 1);
        
        return count;
    }

    private long pow3(int k) {
        
        long r = 1;
        for (int i = 0; i < k; i++) r *= 3;
        
        return r;
    }
}