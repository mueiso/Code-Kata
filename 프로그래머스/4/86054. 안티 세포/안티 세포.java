import java.util.*;

class Solution {
    
    static final long MOD = 1_000_000_007L;

    public int[] solution(int[] a, int[] s) {
        
        int[] answer = new int[s.length];
        int offset = 0;
        for (int t = 0; t < s.length; t++) {
            int len = s[t];
            answer[t] = (int) count(a, offset, len);
            offset += len;
        }
        
        return answer;
    }

    private long count(int[] arr, int off, int n) {
        
        long[] v = new long[n];
        for (int i = 0; i < n; i++) v[i] = arr[off + i];

        long[] pre = new long[n + 1];          // 누적합 (엄격히 증가)
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + v[i];

        int[] K = new int[n];                  // e에서 끝나는 합쳐지는 구간 사슬의 최대 k
        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int e = 0; e < n; e++) {
            long cur = v[e];
            int st = e;
            int k = 0;
            long sum = dp[st];                 // 구간 [e, e]

            while (st > 0) {
                int p = st - 1;
                if (cur % v[p] != 0) break;
                long r = cur / v[p];
                if ((r & (r - 1)) != 0) break; // 2의 거듭제곱이 아님
                int j = Long.numberOfTrailingZeros(r);
                if (j > K[p]) break;           // p에서 끝나는 값 cur인 구간이 합쳐지지 않음

                // p에서 끝나고 합이 cur인 구간의 시작 x: pre[x] = pre[p+1] - cur
                int x = Arrays.binarySearch(pre, 0, p + 1, pre[p + 1] - cur);
                st = x;
                cur *= 2;
                k++;
                sum += dp[st];
            }
            K[e] = k;
            dp[e + 1] = sum % MOD;
        }
        
        return dp[n];
    }
}