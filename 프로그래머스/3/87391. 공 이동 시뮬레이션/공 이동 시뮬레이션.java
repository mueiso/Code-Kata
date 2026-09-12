class Solution {
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        
        long r1 = x, r2 = x;
        long c1 = y, c2 = y;

        // 쿼리를 역순으로 추적
        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            long dx = queries[i][1];

            if (command == 0) { // 좌측 이동 -> 역연산: 우측 이동
                if (c1 != 0) c1 += dx;
                c2 = Math.min((long) m - 1, c2 + dx);
                if (c1 >= m) return 0;
            } else if (command == 1) { // 우측 이동 -> 역연산: 좌측 이동
                if (c2 != m - 1) c2 -= dx;
                c1 = Math.max(0L, c1 - dx);
                if (c2 < 0) return 0;
            } else if (command == 2) { // 상단 이동 -> 역연산: 하단 이동
                if (r1 != 0) r1 += dx;
                r2 = Math.min((long) n - 1, r2 + dx);
                if (r1 >= n) return 0;
            } else if (command == 3) { // 하단 이동 -> 역연산: 상단 이동
                if (r2 != n - 1) r2 -= dx;
                r1 = Math.max(0L, r1 - dx);
                if (r2 < 0) return 0;
            }
        }

        // 가능한 시작점의 개수 (가로 범위 크기 * 세로 범위 크기)
        return (r2 - r1 + 1) * (c2 - c1 + 1);
    }
}