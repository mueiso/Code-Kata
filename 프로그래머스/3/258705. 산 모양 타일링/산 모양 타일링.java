class Solution {
    
    public int solution(int n, int[] tops) {
        
        final int MOD = 10007;
        
        // 기저 상태 (i = -1 시점)
        int a = 1;
        int b = 0;

        for (int i = 0; i < n; i++) {
            int nextA, nextB;

            if (tops[i] == 1) {
                nextA = (a * 3 + b * 2) % MOD;
                nextB = (a + b) % MOD;
            } else {
                nextA = (a * 2 + b) % MOD;
                nextB = (a + b) % MOD;
            }

            a = nextA;
            b = nextB;
        }

        return (a + b) % MOD;
    }
}