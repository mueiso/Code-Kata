class Solution {
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        // 1. 맨해튼 거리 계산 및 탈출 불가능 조건 검사
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (k < dist || (k - dist) % 2 != 0) {
            return "impossible";
        }

        // 2. 사전 순서: d(아래) -> l(왼쪽) -> r(오름쪽) -> u(위)
        char[] dirChar = {'d', 'l', 'r', 'u'};
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};

        StringBuilder sb = new StringBuilder();
        int curX = x;
        int curY = y;

        // 3. k번 동안 탐욕적(Greedy) 이동
        for (int step = 0; step < k; step++) {
            int remK = k - step - 1; // 이번 이동 후 남아있는 이동 횟수

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                // 격자 범위 내에 있는지 확인
                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                    int remDist = Math.abs(nx - r) + Math.abs(ny - c);

                    // 남은 이동 횟수로 목적지에 도달할 수 있는지 확인
                    if (remK >= remDist) {
                        sb.append(dirChar[i]);
                        curX = nx;
                        curY = ny;
                        break; // 가장 앞선 문자를 택했으므로 다음 스텝으로 이동
                    }
                }
            }
        }

        return sb.toString();
    }
}