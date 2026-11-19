class Solution {
    
    public int solution(int[][] visible, int[][] hidden, int k) {
        
        int n = visible.length, m = visible[0].length;
        boolean bothEven = (n % 2 == 0) && (m % 2 == 0);
        final int INF = Integer.MAX_VALUE / 4;
        int answer = Integer.MIN_VALUE;

        for (int mask = 0; mask < (1 << n); mask++) {
            int total = -Integer.bitCount(mask) * k;   // 행 뒤집기 비용
            int bestGain = -INF;                       // max_j (skip_j - best_j)

            for (int j = 0; j < m; j++) {
                int sum0 = 0, sum1 = 0;                 // 열을 안 뒤집음 / 뒤집음
                int min0 = INF, min1 = INF;             // 홀수 칸 최솟값
                for (int i = 0; i < n; i++) {
                    boolean rowFlip = ((mask >> i) & 1) == 1;
                    int v0 = rowFlip ? hidden[i][j] : visible[i][j];
                    int v1 = rowFlip ? visible[i][j] : hidden[i][j];
                    sum0 += v0;
                    sum1 += v1;
                    if (((i + j) & 1) == 1) {
                        min0 = Math.min(min0, v0);
                        min1 = Math.min(min1, v1);
                    }
                }
                int opt0 = sum0, opt1 = sum1 - k;
                int best = Math.max(opt0, opt1);
                total += best;

                if (bothEven) {
                    int skip = Math.max(opt0 - min0, opt1 - min1);
                    bestGain = Math.max(bestGain, skip - best);
                }
            }

            if (bothEven) total += bestGain;
            answer = Math.max(answer, total);
        }
        
        return answer;
    }
}