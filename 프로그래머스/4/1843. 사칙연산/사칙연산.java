class Solution {
    
    public int solution(String arr[]) {
        
        int n = (arr.length + 1) / 2;
        int[] nums = new int[n];
        char[] ops = new char[n - 1];
        
        // 숫자와 연산자 분리
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                nums[i / 2] = Integer.parseInt(arr[i]);
            } else {
                ops[i / 2] = arr[i].charAt(0);
            }
        }
        
        int[][] maxDP = new int[n][n];
        int[][] minDP = new int[n][n];
        
        // 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxDP[i][j] = Integer.MIN_VALUE;
                minDP[i][j] = Integer.MAX_VALUE;
            }
            maxDP[i][i] = nums[i];
            minDP[i][i] = nums[i];
        }
        
        // 구간 길이를 2부터 n까지 늘려가며 DP 수행
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for (int k = i; k < j; k++) {
                    if (ops[k] == '+') {
                        maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i][k] + maxDP[k + 1][j]);
                        minDP[i][j] = Math.min(minDP[i][j], minDP[i][k] + minDP[k + 1][j]);
                    } else { // '-'
                        maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i][k] - minDP[k + 1][j]);
                        minDP[i][j] = Math.min(minDP[i][j], minDP[i][k] - maxDP[k + 1][j]);
                    }
                }
            }
        }
        
        return maxDP[0][n - 1];
    }
}