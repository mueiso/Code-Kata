class Solution {
    
    public int[] solution(int target) {
        
        // dp[i][0] : i 점수를 만드는데 필요한 최소 다트 수
        // dp[i][1] : 최소 다트 수일 때, 최대 '싱글' + '불' 횟수 합
        int[][] dp = new int[target + 1][2];
        
        // 배열 초기화 (0점은 0발이므로 초기화 상태 그대로 둠)
        for (int i = 1; i <= target; i++) {
            dp[i][0] = 100_000_000; // 최소값을 구해야 하므로 임의의 큰 수로 초기화
            dp[i][1] = 0;
        }
        
        // 1. 다트 한 발로 얻을 수 있는 모든 경우의 수 세팅 (점수, 싱글/불 여부)
        int[][] throwsArr = new int[61][2];
        int idx = 0;
        
        throwsArr[idx++] = new int[]{50, 1}; // 불 (50점, 보너스 1)
        for (int i = 1; i <= 20; i++) {
            throwsArr[idx++] = new int[]{i, 1};     // 싱글 (i점, 보너스 1)
            throwsArr[idx++] = new int[]{i * 2, 0}; // 더블 (i*2점, 보너스 0)
            throwsArr[idx++] = new int[]{i * 3, 0}; // 트리플 (i*3점, 보너스 0)
        }
        
        // 2. 1점부터 target 점수까지 최적의 해를 찾아나감 (DP)
        for (int i = 1; i <= target; i++) {
            for (int[] t : throwsArr) {
                int score = t[0]; // 이번에 던져서 깎을 점수
                int isSB = t[1];  // 싱글 또는 불 여부 (1 or 0)
                
                if (i >= score) {
                    // 이번 점수를 던졌다고 가정했을 때의 누적 다트 수와 보너스 수
                    int nextDarts = dp[i - score][0] + 1;
                    int nextSBs = dp[i - score][1] + isSB;
                    
                    // 조건 1. 다트 수가 더 적은 경우 무조건 갱신
                    if (nextDarts < dp[i][0]) {
                        dp[i][0] = nextDarts;
                        dp[i][1] = nextSBs;
                    } 
                    // 조건 2. 다트 수가 같다면, 싱글/불 횟수가 더 많은 쪽으로 갱신
                    else if (nextDarts == dp[i][0] && nextSBs > dp[i][1]) {
                        dp[i][1] = nextSBs;
                    }
                }
            }
        }
        
        return new int[]{dp[target][0], dp[target][1]};
    }
}