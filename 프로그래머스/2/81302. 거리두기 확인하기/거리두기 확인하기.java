class Solution {

    // 상, 하, 좌, 우
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {

        int[] answer = new int[5];

        // 5개의 대기실 검사
        for (int i = 0; i < 5; i++) {

            // 거리두기 지키면 1, 아니면 0
            answer[i] = checkPlace(places[i]) ? 1 : 0;
        }

        return answer;
    }

    /* 대기실 하나 검사 */
    private boolean checkPlace(String[] place) {

        // 모든 좌표 확인
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {

                // 사람 위치 발견
                if (place[x].charAt(y) == 'P') {

                    // 거리두기 위반 확인
                    if (!isValid(place, x, y)) {
                        
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /* 현재 사람 위치 기준 거리두기 검사 */
    private boolean isValid(String[] place, int x, int y) {

        // 1. 상하좌우 거리 1 검사
        for (int d = 0; d < 4; d++) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 체크
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                continue;
            }

            // 바로 옆에 사람 있으면 위반
            if (place[nx].charAt(ny) == 'P') {
                
                return false;
            }
        }

        // 2. 대각선 검사
        int[][] diagonal = {
                {-1, -1},
                {-1, 1},
                {1, -1},
                {1, 1}
        };

        for (int[] d : diagonal) {

            int nx = x + d[0];
            int ny = y + d[1];

            // 범위 체크
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                continue;
            }

            // 대각선에 사람 존재
            if (place[nx].charAt(ny) == 'P') {

                // 사이가 둘 다 파티션이어야 안전
                if (place[x].charAt(ny) != 'X' ||
                    place[nx].charAt(y) != 'X') {
                    
                    return false;
                }
            }
        }

        // 3. 일직선 거리 2 검사
        int[][] straight = {
                {-2, 0},
                {2, 0},
                {0, -2},
                {0, 2}
        };

        for (int[] d : straight) {

            int nx = x + d[0];
            int ny = y + d[1];

            // 범위 체크
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                
                continue;
            }

            // 거리 2 위치에 사람 존재
            if (place[nx].charAt(ny) == 'P') {

                // 중간 위치
                int mx = (x + nx) / 2;
                int my = (y + ny) / 2;

                // 중간이 파티션 아니면 위반
                if (place[mx].charAt(my) != 'X') {
                    
                    return false;
                }
            }
        }

        return true;
    }
}