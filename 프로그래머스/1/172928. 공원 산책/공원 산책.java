class Solution {
    
    public int[] solution(String[] park, String[] routes) {

        int h = park.length;          // 세로
        int w = park[0].length();     // 가로

        // 시작 위치(S) 찾기
        int x = 0, y = 0;
        for (int i = 0; i < h; i++) {
            int j = park[i].indexOf('S');
            if (j != -1) {
                x = i; y = j;
                break;
            }
        }

        // 방향별 이동값 (N,S,W,E)
        for (String route : routes) {
            String[] parts = route.split(" ");
            char dir = parts[0].charAt(0);
            int dist = Integer.parseInt(parts[1]);

            int dx = 0, dy = 0;
            if (dir == 'N') dx = -1;
            else if (dir == 'S') dx = 1;
            else if (dir == 'W') dy = -1;
            else if (dir == 'E') dy = 1;

            // 이번 명령이 가능한지 미리 시뮬레이션(중간 장애물/범위 체크)
            int nx = x, ny = y;
            boolean ok = true;

            for (int step = 0; step < dist; step++) {
                nx += dx;
                ny += dy;

                // 공원 밖이면 실패
                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    ok = false;
                    break;
                }

                // 장애물이면 실패
                if (park[nx].charAt(ny) == 'X') {
                    ok = false;
                    break;
                }
            }

            // 가능하면 실제 위치 갱신, 불가능하면 무시
            if (ok) {
                x = nx;
                y = ny;
            }
        }

        return new int[]{x, y}; // [세로좌표, 가로좌표]
    }
}