import java.util.Arrays;

class Solution {
    
    public int solution(int alp, int cop, int[][] problems) {
        
        int maxAlp = alp;
        int maxCop = cop;

        // 1. 목표로 하는 최대 알고력과 코딩력 탐색
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        // 초기 알고력/코딩력이 이미 목표치보다 높은 경우 보정
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        // 2. DP 테이블 초기화
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], 1_000_000);
        }
        dp[alp][cop] = 0;

        // 3. DP 진행
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 알고리즘 공부 1시간
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                // 코딩 공부 1시간
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 현재 능력치로 풀 수 있는 문제 풀기
                for (int[] p : problems) {
                    int reqAlp = p[0];
                    int reqCop = p[1];
                    int rwdAlp = p[2];
                    int rwdCop = p[3];
                    int cost = p[4];

                    if (i >= reqAlp && j >= reqCop) {
                        int nextAlp = Math.min(maxAlp, i + rwdAlp);
                        int nextCop = Math.min(maxCop, j + rwdCop);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}