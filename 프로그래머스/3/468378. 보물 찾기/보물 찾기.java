import java.util.function.Function;

class Solution {
    
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        
        int w = depth.length;

        // dp[L][R]: L~R 구간에서 보물을 찾기 위한 최소 최악 비용
        int[][] dp = new int[w + 2][w + 2];
        // choice[L][R]: L~R 구간에서 파야 하는 최적의 열 k
        int[][] choice = new int[w + 2][w + 2];

        // 1. 구간 길이를 1부터 w까지 늘려가며 DP 테이블 채우기
        for (int len = 1; len <= w; len++) {
            for (int l = 1; l <= w - len + 1; l++) {
                int r = l + len - 1;

                int minCost = Integer.MAX_VALUE;
                int bestK = l;

                // l부터 r까지 모든 선택지 k 시도
                for (int k = l; k <= r; k++) {
                    int leftCost = dp[l][k - 1];
                    int rightCost = dp[k + 1][r];

                    int cost = depth[k - 1] + Math.max(leftCost, rightCost);

                    if (cost < minCost) {
                        minCost = cost;
                        bestK = k;
                    }
                }

                dp[l][r] = minCost;
                choice[l][r] = bestK;
            }
        }

        // 2. 구축한 DP 트리 경로에 따라 인터랙티브 탐색 수행
        int l = 1, r = w;
        while (l <= r) {
            int k = choice[l][r];
            int res = excavate.apply(k);

            if (res == 0) {
                return k; // 보물 발견
            } else if (res == -1) {
                r = k - 1; // 보물이 왼쪽에 있음
            } else {
                l = k + 1; // 보물이 오른쪽에 있음
            }
        }

        return 0;
    }
}