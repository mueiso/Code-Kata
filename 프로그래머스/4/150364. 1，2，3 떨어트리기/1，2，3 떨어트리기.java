import java.util.*;

class Solution {
    
    public int[] solution(int[][] edges, int[] target) {
        
        int n = target.length;

        // 트리 구성 (자식은 번호 오름차순)
        List<Integer>[] child = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) child[i] = new ArrayList<>();
        for (int[] e : edges) child[e[0]].add(e[1]);
        for (int i = 1; i <= n; i++) Collections.sort(child[i]);

        int[] ptr = new int[n + 1];   // 각 노드가 현재 가리키는 자식 인덱스
        int[] cnt = new int[n + 1];   // 각 리프에 떨어진 숫자 개수
        List<Integer> order = new ArrayList<>(); // k번째 숫자가 도착한 리프

        // 1단계: 시뮬레이션
        while (true) {
            int cur = 1;
            while (!child[cur].isEmpty()) {
                int next = child[cur].get(ptr[cur]);
                ptr[cur] = (ptr[cur] + 1) % child[cur].size();
                cur = next;
            }
            cnt[cur]++;
            order.add(cur);

            // 1만 넣어도 target 초과 → 불가능
            if (cnt[cur] > target[cur - 1]) return new int[]{-1};

            // 모든 리프가 3으로 채우면 target에 도달 가능한지 확인
            boolean done = true;
            for (int v = 1; v <= n; v++) {
                if (child[v].isEmpty() && 3 * cnt[v] < target[v - 1]) {
                    done = false;
                    break;
                }
            }
            if (done) break;
        }

        // 2단계: 리프별로 사전순 최소 숫자열 만들기
        Deque<Integer>[] values = new ArrayDeque[n + 1];
        for (int v = 1; v <= n; v++) {
            if (!child[v].isEmpty() || cnt[v] == 0) continue;
            values[v] = new ArrayDeque<>();
            int t = target[v - 1];
            int c = cnt[v];
            while (c > 0) {
                c--;
                int pick = Math.max(1, t - 3 * c); // 남은 칸을 3으로 채워도 되는 최소값
                values[v].add(pick);
                t -= pick;
            }
        }

        // 떨어진 순서대로 숫자 꺼내기
        int[] answer = new int[order.size()];
        for (int i = 0; i < order.size(); i++) {
            answer[i] = values[order.get(i)].poll();
        }
        
        return answer;
    }
}