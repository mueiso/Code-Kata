import java.util.*;

class Solution {
    
    public int solution(String[] strs, String t) {
        
        Set<String> wordSet = new HashSet<>(Arrays.asList(strs));
        int n = t.length();
        int[] dp = new int[n + 1];
        
        // 충분히 큰 값(INF)으로 초기화
        Arrays.fill(dp, 1000000);
        dp[0] = 0;

        // DP 수행
        for (int i = 1; i <= n; i++) {
            // 단어 조각의 길이는 1 이상 5 이하
            for (int len = 1; len <= 5; len++) {
                if (i - len < 0) continue;

                String sub = t.substring(i - len, i);
                if (wordSet.contains(sub)) {
                    dp[i] = Math.min(dp[i], dp[i - len] + 1);
                }
            }
        }

        return dp[n] >= 1000000 ? -1 : dp[n];
    }
}