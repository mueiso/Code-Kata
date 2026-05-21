import java.util.Arrays;

class Solution {
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 1. 거리를 저장할 2차원 배열 초기화 (충분히 큰 값으로 설정)
        int[][] dist = new int[n + 1][n + 1];
        int INF = 20000001; // 지점 200개 * 요금 10만 = 최대 2천만

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신으로 가는 비용은 0
        }

        // 2. 주어진 요금 정보(간선) 업데이트
        for (int[] fare : fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }

        // 3. 플로이드-워셜 알고리즘 수행 (경유지 k, 출발지 i, 도착지 j)
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 4. 모든 지점을 '합승 종료 지점'으로 가정하여 최소 비용 계산
        int answer = INF;
        for (int i = 1; i <= n; i++) {
            // (S -> i 합승 요금) + (i -> A 요금) + (i -> B 요금)
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }

        return answer;
    }
}