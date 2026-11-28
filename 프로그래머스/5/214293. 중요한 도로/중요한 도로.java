import java.util.*;

class Solution {
    
    int n;
    int[] head, nxt, to;
    long[] wt;

    public int[] solution(int n, int[][] roads) {
        
        this.n = n;
        int m = roads.length;

        // 양방향 인접 리스트
        head = new int[n + 1];
        Arrays.fill(head, -1);
        nxt = new int[2 * m]; to = new int[2 * m]; wt = new long[2 * m];
        int ec = 0;
        for (int[] r : roads) {
            long w = (long) r[2] + r[3];
            to[ec] = r[1]; wt[ec] = w; nxt[ec] = head[r[0]]; head[r[0]] = ec++;
            to[ec] = r[0]; wt[ec] = w; nxt[ec] = head[r[1]]; head[r[1]] = ec++;
        }

        long[] d1 = dijkstra(1);
        long[] dn = dijkstra(n);
        long D = d1[n];

        boolean[] pick = new boolean[m];

        // 1) 교통량 감소로 더 짧아질 수 있는 도로
        for (int i = 0; i < m; i++) {
            int u = roads[i][0], v = roads[i][1];
            long L = roads[i][2], T = roads[i][3];
            if (T > 0) {
                long best = Math.min(d1[u] + L + dn[v], d1[v] + L + dn[u]);
                if (best < D) pick[i] = true;
            }
        }

        // 2) 모든 최단 경로에 포함되는 도로: 거리 구간 겹침 검사
        long[] st = new long[m], en = new long[m];
        boolean[] onDag = new boolean[m];
        List<Long> coords = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = roads[i][0], v = roads[i][1];
            long w = (long) roads[i][2] + roads[i][3];
            if (d1[u] + w + dn[v] == D) {
                onDag[i] = true; st[i] = d1[u]; en[i] = d1[u] + w;
            } else if (d1[v] + w + dn[u] == D) {
                onDag[i] = true; st[i] = d1[v]; en[i] = d1[v] + w;
            }
            if (onDag[i]) { coords.add(st[i]); coords.add(en[i]); }
        }

        long[] cs = coords.stream().mapToLong(Long::longValue).sorted().distinct().toArray();
        int[] diff = new int[cs.length + 1];
        int[] li = new int[m], ri = new int[m];
        for (int i = 0; i < m; i++) {
            if (!onDag[i]) continue;
            li[i] = Arrays.binarySearch(cs, st[i]);
            ri[i] = Arrays.binarySearch(cs, en[i]);
            diff[li[i]]++;
            diff[ri[i]]--;
        }
        int[] cover = new int[cs.length];        // cover[k]: 칸 [cs[k], cs[k+1]]을 덮는 도로 수
        int run = 0;
        for (int k = 0; k < cs.length; k++) { run += diff[k]; cover[k] = run; }

        for (int i = 0; i < m; i++) {
            if (!onDag[i]) continue;
            if (ri[i] == li[i] + 1 && cover[li[i]] == 1) pick[i] = true;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; i++) if (pick[i]) res.add(i + 1);
        if (res.isEmpty()) return new int[]{-1};
        int[] answer = new int[res.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = res.get(i);
        
        return answer;
    }

    private long[] dijkstra(int src) {
        
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((p, q) -> Long.compare(p[0], q[0]));
        pq.add(new long[]{0, src});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[1];
            if (cur[0] > dist[u]) continue;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                long nd = dist[u] + wt[e];
                if (nd < dist[to[e]]) {
                    dist[to[e]] = nd;
                    pq.add(new long[]{nd, to[e]});
                }
            }
        }
        
        return dist;
    }
}