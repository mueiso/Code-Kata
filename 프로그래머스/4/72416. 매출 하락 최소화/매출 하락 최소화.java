import java.util.*;

class Solution {
    
    private int[] cost;
    private List<List<Integer>> tree;
    private int[][] dp;

    public int solution(int[] sales, int[][] links) {
        
        int n = sales.length;
        cost = sales;
        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] link : links) {
            tree.get(link[0]).add(link[1]);
        }

        dp = new int[n + 1][2];
        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private void dfs(int curr) {
        
        dp[curr][0] = 0;
        dp[curr][1] = cost[curr - 1];

        if (tree.get(curr).isEmpty()) {
            return;
        }

        boolean attendedChild = false;
        int minExtraCost = Integer.MAX_VALUE;

        for (int next : tree.get(curr)) {
            dfs(next);
            // 자식이 참석하든 안 하든 더 유리한 비용을 더함
            dp[curr][1] += Math.min(dp[next][0], dp[next][1]);

            if (dp[next][0] < dp[next][1]) {
                // 자식이 참석하지 않는 경우가 더 비용이 적을 때
                dp[curr][0] += dp[next][0];
                minExtraCost = Math.min(minExtraCost, dp[next][1] - dp[next][0]);
            } else {
                // 자식이 참석하는 경우
                dp[curr][0] += dp[next][1];
                attendedChild = true;
            }
        }

        // 팀장이 참석하지 않는데 자식 중 아무도 참석하지 않았다면, 가장 비용이 적게 드는 자식을 강제로 참석시킴
        if (!attendedChild) {
            dp[curr][0] += minExtraCost;
        }
    }
}