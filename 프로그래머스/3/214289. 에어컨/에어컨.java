import java.util.*;

class Solution {
    
    private static final int OFFSET = 10;
    private static final int MAX_TEMP = 50;
    private static final int INF = 1_000_000;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        
        int n = onboard.length;

        // 음수 인덱스 방지를 위한 Offset 적용
        int outdoorTemp = temperature + OFFSET;
        int minTemp = t1 + OFFSET;
        int maxTemp = t2 + OFFSET;

        // dp[i][j] : i분에 온도가 j일 때 소비한 최소 전력
        int[][] dp = new int[n][MAX_TEMP + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][outdoorTemp] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= MAX_TEMP; j++) {
                if (dp[i - 1][j] == INF) continue;

                // 1. 에어컨 OFF
                int nextOffTemp = j;
                if (j < outdoorTemp) nextOffTemp = j + 1;
                else if (j > outdoorTemp) nextOffTemp = j - 1;
                dp[i][nextOffTemp] = Math.min(dp[i][nextOffTemp], dp[i - 1][j]);

                // 2. 에어컨 ON - 온도 유지 (희망온도 == 현재온도)
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + b);

                // 3. 에어컨 ON - 온도 변경 (희망온도 != 현재온도)
                if (j + 1 <= MAX_TEMP) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i - 1][j] + a);
                }
                if (j - 1 >= 0) {
                    dp[i][j - 1] = Math.min(dp[i][j - 1], dp[i - 1][j] + a);
                }
            }

            // 승객이 탑승해 있는 경우, 쾌적 온도 범위를 벗어난 상태 무효화
            if (onboard[i] == 1) {
                for (int j = 0; j <= MAX_TEMP; j++) {
                    if (j < minTemp || j > maxTemp) {
                        dp[i][j] = INF;
                    }
                }
            }
        }

        // 마지막 분에 가능한 최소 소비전력 탐색
        int answer = INF;
        for (int j = 0; j <= MAX_TEMP; j++) {
            answer = Math.min(answer, dp[n - 1][j]);
        }

        return answer;
    }
}