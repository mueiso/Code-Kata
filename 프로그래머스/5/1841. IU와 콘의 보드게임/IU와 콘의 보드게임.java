import java.util.*;

class Solution {
    
    long cross(long[] o, long[] p, long[] q) {
        return (p[0] - o[0]) * (q[1] - o[1]) - (p[1] - o[1]) * (q[0] - o[0]);
    }

    // 꼭짓점 O 주변에서 O→P 방향부터 O→Q 방향으로 각도 순위
    int[] rank(long[] O, long[] P, long[] Q, long[][] v, int n) {
        boolean pos = cross(O, P, Q) > 0;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (i, j) -> {
            if (i.equals(j)) return 0;
            boolean c = cross(O, v[i], v[j]) > 0;
            return (c == pos) ? -1 : 1;
        });
        int[] r = new int[n];
        for (int k = 0; k < n; k++) r[idx[k]] = k;
        return r;
    }

    public int solution(int n, int[][] triangle, int[][] v0) {
        
        if (n == 0) return 1;
        long[] A = {triangle[0][0], triangle[0][1]};
        long[] B = {triangle[1][0], triangle[1][1]};
        long[] C = {triangle[2][0], triangle[2][1]};
        long[][] v = new long[n][];
        for (int i = 0; i < n; i++) v[i] = new long[]{v0[i][0], v0[i][1]};

        int[] ar = rank(A, B, C, v, n);   // A 주변: AB → AC
        int[] br = rank(B, C, A, v, n);   // B 주변: BC → BA
        int[] gr = rank(C, A, B, v, n);   // C 주변: CA → CB

        int[] byA = new int[n], byB = new int[n], byG = new int[n];
        for (int i = 0; i < n; i++) { byA[ar[i]] = i; byB[br[i]] = i; byG[gr[i]] = i; }

        long total = 0;
        boolean[] X = new boolean[n];
        int[] pref = new int[n + 1];
        int[] suf = new int[n + 1];       // suf[b+1] = X=0이고 β>b인 점들의 최대 γ

        for (int a = -1; a < n; a++) {
            for (int i = 0; i < n; i++) X[i] = ar[i] <= a;

            // γ 순위 c의 점이 X=0인지 누적합 (CA 기준점 후보)
            for (int c = 0; c < n; c++) pref[c + 1] = pref[c] + (X[byG[c]] ? 0 : 1);

            suf[n] = -1;
            for (int b = n - 2; b >= -1; b--) {
                int cur = suf[b + 2];
                int q = byB[b + 1];
                if (!X[q]) cur = Math.max(cur, gr[q]);
                suf[b + 1] = cur;
            }

            int m1 = n;                   // X=1이고 β≤b인 점들의 최소 γ
            for (int b = -1; b < n; b++) {
                if (b >= 0) {
                    int q = byB[b];
                    if (X[q]) m1 = Math.min(m1, gr[q]);
                }
                if (a >= 0 && br[byA[a]] <= b) continue;   // AB 기준점은 β > b

                int m0 = suf[b + 1];
                int hi = Math.min(m1, b >= 0 ? gr[byB[b]] : n);   // c < hi
                int lo = Math.max(m0, 0);                          // c ≥ m0
                if (hi > lo) total += pref[hi] - pref[lo];
                if (m0 == -1) total += 1;                          // CA 그룹이 빈 경우
            }
        }
        
        return (int) total;
    }
}