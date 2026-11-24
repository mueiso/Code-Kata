import java.util.*;

class Solution {
    
    public int solution(int[][] t) {
        
        int n = t.length + 1;

        // 인접 리스트 (CSR)
        int[] deg = new int[n + 1];
        for (int[] e : t) { deg[e[0] + 1]++; deg[e[1] + 1]++; }
        for (int i = 0; i < n; i++) deg[i + 1] += deg[i];
        int[] adj = new int[2 * (n - 1)];
        int[] pos = Arrays.copyOf(deg, n);
        for (int[] e : t) { adj[pos[e[0]]++] = e[1]; adj[pos[e[1]]++] = e[0]; }

        // 반복 DFS로 방문 순서와 부모 구하기
        int[] par = new int[n], order = new int[n];
        Arrays.fill(par, -2);
        int[] stack = new int[n];
        int sp = 0, cnt = 0;
        stack[sp++] = 0; par[0] = -1;
        while (sp > 0) {
            int v = stack[--sp];
            order[cnt++] = v;
            for (int p = deg[v]; p < deg[v + 1]; p++) {
                int u = adj[p];
                if (par[u] == -2) { par[u] = v; stack[sp++] = u; }
            }
        }

        int[] d = new int[n], f = new int[n], up = new int[n];

        // 아래에서 위로: d, f
        for (int k = n - 1; k >= 0; k--) {
            int v = order[k];
            int d1 = 0, d2 = 0;
            for (int p = deg[v]; p < deg[v + 1]; p++) {
                int c = adj[p];
                if (c == par[v]) continue;
                if (d[c] > d1) { d2 = d1; d1 = d[c]; }
                else if (d[c] > d2) d2 = d[c];
            }
            d[v] = 1 + d1;
            int best = d1;                               // v에서 끝, 곁가지 하나
            for (int p = deg[v]; p < deg[v + 1]; p++) {
                int c = adj[p];
                if (c == par[v]) continue;
                int other = (d[c] == d1) ? d2 : d1;      // c를 뺀 최대 d
                best = Math.max(best, f[c] + other);
            }
            f[v] = 1 + best;
        }

        // 위에서 아래로: up
        for (int k = 0; k < n; k++) {
            int v = order[k];
            int d1 = 0, d2 = 0;
            for (int p = deg[v]; p < deg[v + 1]; p++) {
                int c = adj[p];
                if (c == par[v]) continue;
                if (d[c] > d1) { d2 = d1; d1 = d[c]; }
                else if (d[c] > d2) d2 = d[c];
            }
            for (int p = deg[v]; p < deg[v + 1]; p++) {
                int c = adj[p];
                if (c == par[v]) continue;
                int other = (d[c] == d1) ? d2 : d1;
                up[c] = 1 + Math.max(up[v], other);
            }
        }

        // 각 정점을 본선의 꼭대기로 하는 경우
        int answer = 0;
        for (int v = 0; v < n; v++) {
            // d 상위 3개 자식, f 상위 3개 자식 (id 포함)
            int[] dv = {0, 0, 0}, di = {-1, -1, -1};
            int[] fv = {0, 0, 0}, fi = {-1, -1, -1};
            for (int p = deg[v]; p < deg[v + 1]; p++) {
                int c = adj[p];
                if (c == par[v]) continue;
                insert(dv, di, d[c], c);
                insert(fv, fi, f[c], c);
            }
            // 곁가지 후보 x: 없음(-1) 또는 d 상위 3개 자식
            for (int xi = -1; xi < 3; xi++) {
                int x = (xi == -1) ? -1 : di[xi];
                if (xi >= 0 && x == -1) continue;
                int side = Math.max(up[v], x == -1 ? 0 : d[x]);
                // x를 제외한 f 상위 2개
                int s = 0, taken = 0;
                for (int j = 0; j < 3 && taken < 2; j++) {
                    if (fi[j] == -1) break;
                    if (fi[j] == x) continue;
                    s += fv[j];
                    taken++;
                }
                answer = Math.max(answer, 1 + s + side);
            }
        }
        
        return answer;
    }

    /* 내림차순 상위 3개 유지 */
    private void insert(int[] val, int[] id, int v, int c) {
        
        if (v > val[0] || id[0] == -1) {
            val[2] = val[1]; id[2] = id[1];
            val[1] = val[0]; id[1] = id[0];
            val[0] = v; id[0] = c;
        } else if (v > val[1] || id[1] == -1) {
            val[2] = val[1]; id[2] = id[1];
            val[1] = v; id[1] = c;
        } else if (v > val[2] || id[2] == -1) {
            val[2] = v; id[2] = c;
        }
    }
}