import java.util.*;

class Solution {
    
    int[] cnt;
    long[] len;
    long[] ys;     // 압축된 y 좌표
    int M;         // 기본 구간 개수 = ys.length - 1

    public long solution(int[][] rectangles) {
        
        int n = rectangles.length;

        // y 좌표 압축
        long[] tmp = new long[2 * n];
        for (int i = 0; i < n; i++) {
            tmp[2 * i] = rectangles[i][1];
            tmp[2 * i + 1] = rectangles[i][3];
        }
        Arrays.sort(tmp);
        int sz = 0;
        for (int i = 0; i < tmp.length; i++)
            if (i == 0 || tmp[i] != tmp[i - 1]) tmp[sz++] = tmp[i];
        ys = Arrays.copyOf(tmp, sz);
        M = sz - 1;

        // 이벤트: {x, y1인덱스, y2인덱스, +1/-1}
        long[][] ev = new long[2 * n][];
        for (int i = 0; i < n; i++) {
            int[] r = rectangles[i];
            int lo = Arrays.binarySearch(ys, r[1]);
            int hi = Arrays.binarySearch(ys, r[3]);
            ev[2 * i] = new long[]{r[0], lo, hi, 1};
            ev[2 * i + 1] = new long[]{r[2], lo, hi, -1};
        }
        Arrays.sort(ev, (p, q) -> Long.compare(p[0], q[0]));

        cnt = new int[4 * Math.max(M, 1)];
        len = new long[4 * Math.max(M, 1)];

        long answer = 0;
        long prevX = ev[0][0];
        for (long[] e : ev) {
            answer += len[1] * (e[0] - prevX);   // 이전 x ~ 현재 x 사이 넓이
            prevX = e[0];
            update(1, 0, M - 1, (int) e[1], (int) e[2] - 1, (int) e[3]);
        }
        return answer;
    }

    /* 기본 구간 [l, r] (구간 i = ys[i] ~ ys[i+1])에 v를 더함 */
    private void update(int node, int s, int e, int l, int r, int v) {
        
        if (r < s || e < l) return;
        if (l <= s && e <= r) {
            cnt[node] += v;
        } else {
            int mid = (s + e) / 2;
            update(node * 2, s, mid, l, r, v);
            update(node * 2 + 1, mid + 1, e, l, r, v);
        }
        if (cnt[node] > 0) len[node] = ys[e + 1] - ys[s];
        else if (s == e) len[node] = 0;
        else len[node] = len[node * 2] + len[node * 2 + 1];
    }
}