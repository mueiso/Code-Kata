import java.util.*;

class Solution {
    
    List<Integer>[] ch1, ch2;
    int[][] memo;

    public int solution(int n1, int[][] g1, int n2, int[][] g2) {
        
        ch1 = buildChildren(n1, g1);
        ch2 = buildChildren(n2, g2);
        memo = new int[n1 + 1][n2 + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        return f(1, 1);
    }

    /* 루트 1 기준으로 부모-자식 방향 정하기  */
    private List<Integer>[] buildChildren(int n, int[][] g) {
        
        List<Integer>[] adj = new ArrayList[n + 1];
        List<Integer>[] ch = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) { adj[i] = new ArrayList<>(); ch[i] = new ArrayList<>(); }
        for (int[] e : g) { adj[e[0]].add(e[1]); adj[e[1]].add(e[0]); }

        boolean[] seen = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1); seen[1] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (!seen[v]) { seen[v] = true; ch[u].add(v); q.add(v); }
            }
        }
        
        return ch;
    }

    private int f(int u, int v) {
        
        if (memo[u][v] != -1) return memo[u][v];
        List<Integer> A = ch1[u], B = ch2[v];
        int res = 1;
        if (!A.isEmpty() && !B.isEmpty()) {
            boolean swap = A.size() > B.size();      // 행 ≤ 열이 되도록
            List<Integer> R = swap ? B : A, C = swap ? A : B;
            int r = R.size(), c = C.size();
            int[][] w = new int[r][c];
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    w[i][j] = swap ? f(C.get(j), R.get(i)) : f(R.get(i), C.get(j));
            res += maxWeightMatching(w, r, c);
        }
        
        return memo[u][v] = res;
    }

    /* 헝가리안 알고리즘 (r ≤ c), 가중치 합 최대 */
    private int maxWeightMatching(int[][] w, int r, int c) {
        
        final int INF = Integer.MAX_VALUE / 2;
        int[] uPot = new int[r + 1], vPot = new int[c + 1];
        int[] p = new int[c + 1], way = new int[c + 1];   // p[j]: 열 j에 매칭된 행 (1-indexed)

        for (int i = 1; i <= r; i++) {
            p[0] = i;
            int j0 = 0;
            int[] minv = new int[c + 1];
            boolean[] used = new boolean[c + 1];
            Arrays.fill(minv, INF);
            do {
                used[j0] = true;
                int i0 = p[j0], delta = INF, j1 = 0;
                for (int j = 1; j <= c; j++) {
                    if (used[j]) continue;
                    int cur = -w[i0 - 1][j - 1] - uPot[i0] - vPot[j];   // 비용 = -가중치
                    if (cur < minv[j]) { minv[j] = cur; way[j] = j0; }
                    if (minv[j] < delta) { delta = minv[j]; j1 = j; }
                }
                for (int j = 0; j <= c; j++) {
                    if (used[j]) { uPot[p[j]] += delta; vPot[j] -= delta; }
                    else minv[j] -= delta;
                }
                j0 = j1;
            } while (p[j0] != 0);
            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        int total = 0;
        for (int j = 1; j <= c; j++)
            if (p[j] != 0) total += w[p[j] - 1][j - 1];
        
        return total;
    }
}