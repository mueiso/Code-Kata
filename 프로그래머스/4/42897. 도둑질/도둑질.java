class Solution {
    
    public int solution(int[] money) {
        
        int n = money.length;
        
        if (n == 3) {
            int max = money[0];
            for (int m : money) {
                max = Math.max(max, m);
            }
            return max;
        }

        // Case 1: 첫 번째 집을 털고, 마지막 집을 털지 않는 경우 (0 ~ n-2)
        int case1 = robRange(money, 0, n - 2);

        // Case 2: 첫 번째 집을 털지 않고, 마지막 집을 고려하는 경우 (1 ~ n-1)
        int case2 = robRange(money, 1, n - 1);

        return Math.max(case1, case2);
    }

    /* 특정 범위(start ~ end) 내에서 인접한 집을 털지 않고 훔칠 수 있는 돈의 최댓값을 구하는 메서드 */
    private int robRange(int[] money, int start, int end) {
        
        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}