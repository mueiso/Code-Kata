import java.util.*;

class Solution
{
    int[] ea, eb;          // 간선 양 끝점
    int[] xe, ye;          // 정점별 X 간선(M0에만), Y 간선(Mt에만), 없으면 -1
    boolean[] isX;         // 간선이 X인지
    boolean[] used;

    public int[][] solution(int n, int m, int[] a, int[] b, int k, int m1, int m2, int[] e1, int[] e2)
    {
        ea = new int[m + 1]; eb = new int[m + 1];
        for (int i = 1; i <= m; i++) { ea[i] = a[i - 1]; eb[i] = b[i - 1]; }

        boolean[] in0 = new boolean[m + 1], inT = new boolean[m + 1];
        for (int e : e1) in0[e] = true;
        for (int e : e2) inT[e] = true;

        xe = new int[n + 1]; ye = new int[n + 1];
        Arrays.fill(xe, -1); Arrays.fill(ye, -1);
        isX = new boolean[m + 1];
        used = new boolean[m + 1];
        List<Integer> diffX = new ArrayList<>();

        for (int e : e1) if (!inT[e]) {
            isX[e] = true; xe[ea[e]] = e; xe[eb[e]] = e; diffX.add(e);
        }
        for (int e : e2) if (!in0[e]) {
            ye[ea[e]] = e; ye[eb[e]] = e;
        }

        List<int[]> inc = new ArrayList<>(), bal = new ArrayList<>(), dec = new ArrayList<>();

        // 1) 경로: 대칭차 간선이 하나만 붙은 정점에서 시작
        for (int v = 1; v <= n; v++) {
            int cntDeg = (xe[v] != -1 ? 1 : 0) + (ye[v] != -1 ? 1 : 0);
            if (cntDeg != 1) continue;
            int first = xe[v] != -1 ? xe[v] : ye[v];
            if (used[first]) continue;
            int[] path = walk(v, first);
            // 한쪽 끝이 Y라면 Y 끝에서 시작하도록
            if (isX[path[0]] && !isX[path[path.length - 1]]) reverse(path);
            int cx = 0;
            for (int e : path) if (isX[e]) cx++;
            int cy = path.length - cx;
            if (cy > cx) inc.add(path);
            else if (cy == cx) bal.add(path);
            else dec.add(path);
        }
        // 2) 남은 것은 사이클: X 간선에서 시작
        for (int e : diffX) {
            if (used[e]) continue;
            bal.add(walk(ea[e], e));
        }

        List<int[]> steps = new ArrayList<>();
        for (int[] c : inc) emit(c, steps);
        for (int[] c : bal) emit(c, steps);
        for (int[] c : dec) emit(c, steps);

        int[][] answer = new int[steps.size()][];
        for (int i = 0; i < steps.size(); i++) answer[i] = steps.get(i);
        return answer;
    }

    /* 정점 start에서 간선 first로 출발해 X/Y를 번갈아 따라감 */
    private int[] walk(int start, int first) {
        
        List<Integer> list = new ArrayList<>();
        int v = start, e = first;
        while (e != -1 && !used[e]) {
            used[e] = true;
            list.add(e);
            int u = (ea[e] == v) ? eb[e] : ea[e];   // 반대쪽 정점
            e = isX[e] ? ye[u] : xe[u];             // 다음은 반대 종류 간선
            v = u;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) res[i] = list.get(i);
        return res;
    }

    private void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
        }
    }

    /* 순서대로: Y를 넣기 전에 바로 다음 X를 지움, 맨 앞이 X면 먼저 지움 */
    private void emit(int[] seq, List<int[]> steps) {
        
        int i = 0;
        if (seq.length > 0 && isX[seq[0]]) {
            steps.add(new int[]{0, seq[0]});
            i = 1;
        }
        while (i < seq.length) {
            int y = seq[i];                          // 여기는 항상 Y
            if (i + 1 < seq.length) steps.add(new int[]{0, seq[i + 1]});
            steps.add(new int[]{1, y});
            i += 2;
        }
    }
}