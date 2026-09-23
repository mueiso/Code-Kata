class Solution {
    
    public int solution(int[][] cost, int[][] hint) {
        
        int n = cost.length;
        int numBundles = n - 1;

        // 1. 각 번들별로 포함된 힌트권 수 미리 집계
        // bundleCoupons[b][s]: b번 번들에 포함된 s번 스테이지(0-based) 힌트권 개수
        int[][] bundleCoupons = new int[numBundles][n];
        for (int b = 0; b < numBundles; b++) {
            for (int k = 1; k < hint[b].length; k++) {
                int targetStage = hint[b][k] - 1; // 0-based 변환
                bundleCoupons[b][targetStage]++;
            }
        }

        int minTotalCost = Integer.MAX_VALUE;
        int maxMask = 1 << numBundles; // 2^(n-1)

        // 2. 모든 번들 구매 조합 비트마스크 탐색
        for (int mask = 0; mask < maxMask; mask++) {
            int currentCost = 0;
            int[] stageCoupons = new int[n];

            for (int b = 0; b < numBundles; b++) {
                if ((mask & (1 << b)) != 0) {
                    // 번들 구매 비용 추가
                    currentCost += hint[b][0];

                    // 번들에 포함된 힌트권을 누적
                    for (int s = b + 1; s < n; s++) {
                        stageCoupons[s] += bundleCoupons[b][s];
                    }
                }
            }

            // 3. 각 스테이지 해결 비용 계산
            for (int s = 0; s < n; s++) {
                int usedCoupons = Math.min(stageCoupons[s], n - 1);
                currentCost += cost[s][usedCoupons];
            }

            // 최소 비용 갱신
            minTotalCost = Math.min(minTotalCost, currentCost);
        }

        return minTotalCost;
    }
}