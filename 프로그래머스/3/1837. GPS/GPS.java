import java.util.*;

class Solution {
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        
        // 1. 인접 리스트 구축 (제자리 이동 포함)
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            adj[i].add(i); // 자기 자신으로 머무르는 이동 허용
        }

        for (int[] edge : edge_list) {
            int u = edge[0];
            int v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        final int INF = 1_000_000_000;

        // 2. DP 테이블 초기화 (k x (n + 1))
        int[][] dp = new int[k][n + 1];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], INF);
        }

        // 시작 거점(t = 0) 설정
        dp[0][gps_log[0]] = 0;

        // 3. DP 점화식 계산
        for (int t = 1; t < k; t++) {
            for (int curr = 1; curr <= n; curr++) {
                // 이전 거점(prev)에서 curr 거점으로 이동 가능한 경로 중 최솟값 탐색
                for (int prev : adj[curr]) {
                    if (dp[t - 1][prev] != INF) {
                        dp[t][curr] = Math.min(dp[t][curr], dp[t - 1][prev]);
                    }
                }

                // 현재 거점이 실제 gps_log[t]와 다르면 수정 횟수 +1
                if (dp[t][curr] != INF && curr != gps_log[t]) {
                    dp[t][curr]++;
                }
            }
        }

        // 4. 도착 거점(t = k - 1)에서의 최소 수정 횟수 확인
        int answer = dp[k - 1][gps_log[k - 1]];
        return (answer >= INF) ? -1 : answer;
    }
}