import java.util.Arrays;

class Solution {
    
    public int solution(int[][] info, int n, int m) {
        
        final int INF = 100000; // 도달할 수 없음을 나타내는 값
        
        // dp[j]: B도둑의 누적 흔적이 j일 때, A도둑의 최소 누적 흔적
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0; // 초기 상태: 두 도둑 모두 흔적 0

        for (int[] item : info) {
            int aTrace = item[0];
            int bTrace = item[1];
            
            int[] nextDp = new int[m];
            Arrays.fill(nextDp, INF);

            for (int j = 0; j < m; j++) {
                if (dp[j] == INF) continue; // 불가능한 상태는 건너뜀

                // 1. A도둑이 물건을 훔치는 경우
                nextDp[j] = Math.min(nextDp[j], dp[j] + aTrace);

                // 2. B도둑이 물건을 훔치는 경우 (B의 흔적이 m 미만이어야 함)
                if (j + bTrace < m) {
                    nextDp[j + bTrace] = Math.min(nextDp[j + bTrace], dp[j]);
                }
            }
            dp = nextDp; // 다음 상태로 갱신
        }

        // 최종 결과 탐색: A도둑의 흔적이 n 미만인 경우 중 최솟값 찾기
        int answer = INF;
        for (int j = 0; j < m; j++) {
            if (dp[j] < n) {
                answer = Math.min(answer, dp[j]);
            }
        }

        return answer == INF ? -1 : answer;
    }
}