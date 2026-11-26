import java.util.*;

class Solution {
    
    int[] parent, size;
    // 그룹 대표(루트) 기준
    int[] time, setOf, prv, nxt;
    // 집합 기준
    int[] head, tail;

    int find(int x) {
        int r = x;
        while (parent[r] != r) r = parent[r];
        while (parent[x] != r) { int nx = parent[x]; parent[x] = r; x = nx; }
        
        return r;
    }

    // 두 루트를 합치고 새 루트 반환
    int union(int a, int b) {
        if (size[a] < size[b]) { int t = a; a = b; b = t; }
        parent[b] = a;
        size[a] += size[b];
        
        return a;
    }

    public String[] solution(int n, int[][] queries) {
        
        int q = queries.length;
        parent = new int[n]; size = new int[n];
        time = new int[n]; setOf = new int[n]; prv = new int[n]; nxt = new int[n];
        head = new int[n + q + 1]; tail = new int[n + q + 1];

        for (int i = 0; i < n; i++) {
            parent[i] = i; size[i] = 1;
            time[i] = 0; setOf[i] = i;
            prv[i] = -1; nxt[i] = -1;
            head[i] = i; tail[i] = i;
        }
        int setCnt = n;
        List<String> res = new ArrayList<>();

        for (int qi = 0; qi < q; qi++) {
            int type = queries[qi][0], x = queries[qi][1], y = queries[qi][2];
            // 몇 번째 쿼리인지
            int now = qi + 1;
            int rx = find(x), ry = find(y);

            if (type == 1) {
                int sx = setOf[rx], sy = setOf[ry];
                if (sx == sy) continue;

                // y 집합의 모든 그룹을 하나로 합침
                int g = head[sy];
                int r = g;
                g = nxt[g];
                while (g != -1) {
                    int next = nxt[g];
                    r = union(r, g);
                    g = next;
                }
                head[sy] = tail[sy] = -1;

                // x 집합 꼬리에 붙임
                time[r] = now;
                setOf[r] = sx;
                prv[r] = tail[sx];
                nxt[r] = -1;
                nxt[tail[sx]] = r;
                tail[sx] = r;

            } else if (type == 2) {
                if (time[rx] > time[ry]) continue;  // 옮길 원소 없음
                int s = setOf[rx];
                int before = prv[rx], after = nxt[ry];

                // rx ~ ry 구간 그룹들을 하나로 합침
                int r = rx;
                int g = rx;
                while (g != ry) {
                    int next = nxt[g];
                    if (r != next) r = union(r, next);
                    g = next;
                }

                // 원래 집합에서 구간 떼어내기
                if (before != -1) nxt[before] = after; else head[s] = after;
                if (after != -1) prv[after] = before; else tail[s] = before;

                // 새 집합 생성
                int ns = setCnt++;
                time[r] = now;
                setOf[r] = ns;
                prv[r] = -1; nxt[r] = -1;
                head[ns] = tail[ns] = r;

            } else {
                res.add(setOf[rx] == setOf[ry] ? "Yes" : "No");
            }
        }
        
        return res.toArray(new String[0]);
    }
}