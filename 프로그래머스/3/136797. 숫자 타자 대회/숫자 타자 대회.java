class Solution {
    
    public int solution(String numbers) {
        
        // 1. 숫자 간의 최소 이동 가중치 사전 계산
        int[][] cost = new int[10][10];
        
        // 0부터 9까지의 좌표 맵핑 (0은 가장 아래쪽 중앙)
        int[][] pos = {
            {3, 1}, // 0
            {0, 0}, {0, 1}, {0, 2}, // 1, 2, 3
            {1, 0}, {1, 1}, {1, 2}, // 4, 5, 6
            {2, 0}, {2, 1}, {2, 2}  // 7, 8, 9
        };
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) {
                    cost[i][j] = 1; // 제자리 누르기
                } else {
                    int dr = Math.abs(pos[i][0] - pos[j][0]);
                    int dc = Math.abs(pos[i][1] - pos[j][1]);
                    
                    int diag = Math.min(dr, dc);   // 대각선 이동 횟수
                    int straight = Math.abs(dr - dc); // 직선 이동 횟수
                    
                    cost[i][j] = diag * 3 + straight * 2;
                }
            }
        }
        
        // 2. DP 테이블 초기화
        int INF = 987654321;
        int[][] dp = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = INF;
            }
        }
        
        // 초기 상태: 왼손 4, 오른손 6
        dp[4][6] = 0;
        
        // 3. 문자열을 순회하며 DP 상태 갱신
        int len = numbers.length();
        for (int i = 0; i < len; i++) {
            int target = numbers.charAt(i) - '0';
            
            // 다음 단계를 위한 새로운 DP 테이블
            int[][] nextDp = new int[10][10];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    nextDp[j][k] = INF;
                }
            }
            
            // 기존 DP 테이블을 순회하며 가능한 모든 상태에서 분기
            for (int L = 0; L < 10; L++) {
                for (int R = 0; R < 10; R++) {
                    if (dp[L][R] == INF) continue; // 도달할 수 없는 상태는 패스
                    
                    // 조건: 두 손가락이 같은 위치에 겹칠 수 없음
                    
                    // [왼손]을 target으로 이동시키는 경우
                    if (R != target) {
                        nextDp[target][R] = Math.min(nextDp[target][R], dp[L][R] + cost[L][target]);
                    }
                    
                    // [오른손]을 target으로 이동시키는 경우
                    if (L != target) {
                        nextDp[L][target] = Math.min(nextDp[L][target], dp[L][R] + cost[R][target]);
                    }
                }
            }
            // 테이블 교체
            dp = nextDp;
        }
        
        // 4. 마지막에 기록된 상태들 중 최솟값 찾기
        int minCost = INF;
        for (int L = 0; L < 10; L++) {
            for (int R = 0; R < 10; R++) {
                minCost = Math.min(minCost, dp[L][R]);
            }
        }
        
        return minCost;
    }
}