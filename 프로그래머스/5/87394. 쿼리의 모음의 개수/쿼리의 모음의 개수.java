import java.util.*;

class Solution {
    
    static final long MOD = 998_244_353L;
    int[] a;

    public int solution(int q, int[] a) {
        
        this.a = a;
        int n = a.length;

        // T = 모든 구간 최솟값의 합 (가능한 쿼리 수)
        long T = 0;
        for (int l = 0; l < n; l++) {
            int m = Integer.MAX_VALUE;
            for (int r = l; r < n; r++) {
                m = Math.min(m, a[r]);
                T += m;
            }
        }

        long[] poly = G(0, n - 1);
        long answer = 0;
        for (int k = 0; k < poly.length; k++) {
            if (poly[k] == 0) continue;
            answer = (answer + poly[k] * pow((T - k) % MOD, q)) % MOD;
        }
        return (int) answer;
    }

    long w(long len) { return len * (len + 1) / 2; }

    // Σ_{S ⊆ [L,R]} (-1)^|S| y^{B(S)}
    long[] G(int L, int R) {
        if (L > R) return new long[]{1};

        int m = Integer.MAX_VALUE;
        for (int i = L; i <= R; i++) m = Math.min(m, a[i]);
        List<Integer> P = new ArrayList<>();
        for (int i = L; i <= R; i++) if (a[i] == m) P.add(i);

        // 최솟값 위치 사이의 틈들: 재귀 결과를 곱함
        long[] H = {1};
        int prev = L - 1;
        for (int idx = 0; idx <= P.size(); idx++) {
            int p = (idx < P.size()) ? P.get(idx) : R + 1;
            H = mul(H, G(prev + 1, p - 1));
            prev = p;
        }

        // 최솟값 위치 중 S로 고를 것들: 피하는 구간 수 u에 대한 DP
        int tot = (int) w(R - L + 1);
        int k = P.size();
        int[] pts = new int[k + 1];
        pts[0] = L - 1;
        for (int i = 0; i < k; i++) pts[i + 1] = P.get(i);

        long[][] dp = new long[k + 1][];
        dp[0] = new long[tot + 1];
        dp[0][0] = 1;
        long[] Q = new long[tot + 1];            // y^{tot - u}의 계수

        for (int j = 0; j <= k; j++) {
            if (dp[j] == null) continue;
            for (int nx = j + 1; nx <= k; nx++) {
                int sh = (int) w(pts[nx] - pts[j] - 1);
                if (dp[nx] == null) dp[nx] = new long[tot + 1];
                for (int e = 0; e + sh <= tot; e++) {
                    if (dp[j][e] == 0) continue;
                    dp[nx][e + sh] = (dp[nx][e + sh] - dp[j][e] + MOD) % MOD;   // 하나 더 고름: 부호 반전
                }
            }
            int sh = (int) w(R - pts[j]);        // 마지막 고른 위치 ~ R 사이 빈칸
            for (int e = 0; e + sh <= tot; e++) {
                if (dp[j][e] == 0) continue;
                int deg = tot - (e + sh);
                Q[deg] = (Q[deg] + dp[j][e]) % MOD;
            }
        }
        return mul(H, Q);
    }

    long[] mul(long[] A, long[] B) {
        long[] C = new long[A.length + B.length - 1];
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) continue;
            for (int j = 0; j < B.length; j++) {
                C[i + j] = (C[i + j] + A[i] * B[j]) % MOD;
            }
        }
        return C;
    }

    long pow(long b, long e) {
        b %= MOD;
        if (b < 0) b += MOD;
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = r * b % MOD;
            b = b * b % MOD;
            e >>= 1;
        }
        return r;
    }
}