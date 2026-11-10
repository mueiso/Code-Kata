import java.util.*;

class Solution {
    
    public int solution(int[] heights) {
        
        int n = heights.length;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = heights[i];
        Arrays.sort(a);

        final long INF = Long.MAX_VALUE;
        long best = 0;

        // k: 연속 구간 B의 크기
        for (int k = 1; k <= n; k++) {
            int e = (n - k) - (k - 1);          // 양 끝에 남는 자리 수 (0~2)
            if (e < 0 || e > 2) continue;

            for (int st = 0; st <= 1; st++) {   // st: 맨 앞 자리에 s를 둘지
                int en = e - st;                // en: 맨 뒤 자리에 p를 둘지
                if (en < 0 || en > 1) continue;

                int dS = k + st - 1;            // S 연결부의 인덱스 차이
                int dP = n - k - st;            // P 연결부의 인덱스 차이
                int hiS = n - k - st;

                // S 쪽: a[t+dS] - a[t] 의 접미 최솟값
                long[] sufS = new long[n + 2];
                Arrays.fill(sufS, INF);
                for (int t = hiS; t >= 0; t--) {
                    long g = (t + dS < n) ? a[t + dS] - a[t] : INF;
                    sufS[t] = Math.min(sufS[t + 1], g);
                }
                // P 쪽: a[u+dP] - a[u] 의 접두 최솟값
                long[] preP = new long[n + 1];
                preP[0] = INF;
                for (int u = 0; u < n; u++) {
                    long g = (u + dP < n) ? a[u + dP] - a[u] : INF;
                    preP[u + 1] = Math.min(preP[u], g);
                }

                // l: 구간 B의 시작 위치 (P = a[0..l-1], B = a[l..l+k-1], S = 나머지)
                for (int l = 0; l + k <= n; l++) {
                    int m = n - l - k;          // |S|
                    if (m < st || l < en) continue;

                    long v = INF;
                    if (st == 1) v = Math.min(v, a[l + k] - a[l]);         // 맨 앞 s - b1
                    if (en == 1) v = Math.min(v, a[l + k - 1] - a[l - 1]); // bk - 맨 뒤 p
                    if (l + 1 <= hiS) v = Math.min(v, sufS[l + 1]);
                    v = Math.min(v, preP[l - en]);

                    best = Math.max(best, v);
                }
            }
        }
        
        return (int) best;
    }
}