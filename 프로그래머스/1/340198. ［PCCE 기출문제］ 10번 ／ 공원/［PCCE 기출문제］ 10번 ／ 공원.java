import java.util.Arrays;

class Solution {
    
    public int solution(int[] mats, String[][] park) {
        
        int n = park.length;    // 행 개수
        int m = park[0].length; // 열 개수
        
        // dp[i][j] = (i, j)를 오른쪽 아래 꼭짓점으로 하는 만들 수 있는 가장 큰 정사각형 한 변의 길이
        int[][] dp = new int[n][m];
        
        int maxSize = 0;        // 공원에서 만들 수 있는 최대 정사각형 크기
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                
                // 현재 칸이 비어 있다면
                if(park[i][j].equals("-1")) {
                    
                    // 첫 행 또는 첫 열은 확장할 수 없으므로 1
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // 위, 왼쪽, 왼쪽 위 중 가장 작은 값 +1 (정사각형이 커지려면 세 방향이 모두 가능해야 함)
                        dp[i][j] = Math.min(
                            Math.min(dp[i-1][j], dp[i][j-1]),
                            dp[i-1][j-1]
                        ) + 1;
                    }
                    
                    // 최대 크기 갱신
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        
        // 공원에서 만들 수 있는 최대 크기 이하인 것 중 내가 가진 돗자리 중 가장 큰 것 찾기
        Arrays.sort(mats);
        
        for(int i = mats.length - 1; i >= 0; i--) {
            if(mats[i] <= maxSize) {
                return mats[i];
            }
        }
        
        // 아무 돗자리도 못 깔면
        return -1;
    }
}

/*
 * min +1인 이유
   정사각형이 되려면 위쪽도 최소 2, 왼쪽도 최소 2, 대각선도 최소 2 이어야 확장 가능
   현재칸 = min(위, 왼쪽, 대각선) + 1
 
 * dp[i][j]에서 (i, j)를 오른쪽 아래 꼭짓점으로 하는 만들 수 있는 가장 큰 정사각형의 한 변 길이라고 정의하고,
 * 만약 park[i][j]가 빈칸이면
   dp[i][j] =
   min(
       dp[i-1][j],     // 위
       dp[i][j-1],     // 왼쪽
       dp[i-1][j-1]    // 대각선
   ) + 1
   정사각형이 커지려면 위, 왼쪽, 대각선 모두 커야 하기 때문
 */