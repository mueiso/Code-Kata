class Solution {
    
    public int solution(int n, int[][] results) {
        
        // 승패 결과를 담을 2차원 배열 (1: 승리, -1: 패배, 0: 모름)
        int[][] graph = new int[n + 1][n + 1];

        // 1. 주어진 경기 결과 반영
        for (int[] edge : results) {
            int winner = edge[0];
            int loser = edge[1];
            graph[winner][loser] = 1;
            graph[loser][winner] = -1;
        }

        // 2. 플로이드-워셜 알고리즘으로 승패 관계 유추
        for (int k = 1; k <= n; k++) { // 거쳐가는 선수 (중간 매개체)
            for (int i = 1; i <= n; i++) { // 기준 선수
                for (int j = 1; j <= n; j++) { // 상대 선수
                    if (i == j) continue;
                    
                    // i가 k를 이기고, k가 j를 이겼다면 -> i는 j를 이긴다.
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                        graph[j][i] = -1;
                    }
                }
            }
        }

        // 3. 정확한 순위를 알 수 있는 선수 카운트
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != 0) { // 승/패를 아는 경우
                    count++;
                }
            }
            // 나를 제외한 나머지(n - 1)명과의 승패를 모두 안다면 순위 확정
            if (count == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}