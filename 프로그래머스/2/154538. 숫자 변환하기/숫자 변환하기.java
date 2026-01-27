import java.util.*;

class Solution {
    
    public int solution(int x, int y, int n) {
        
        // x가 이미 y인 경우
        if (x == y) return 0;

        // 방문 여부 체크 (y까지만 필요)
        boolean[] visited = new boolean[y + 1];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, 0});
        visited[x] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int value = cur[0];
            int cnt = cur[1];

            // 가능한 다음 연산들
            int[] nexts = {value + n, value * 2, value * 3};

            for (int next : nexts) {
                // 범위 초과 or 이미 방문
                if (next > y || visited[next]) continue;

                // 목표 도달
                if (next == y) return cnt + 1;

                visited[next] = true;
                queue.add(new int[]{next, cnt + 1});
            }
        }

        // y를 만들 수 없는 경우
        return -1;
    }
}