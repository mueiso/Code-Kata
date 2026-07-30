import java.util.ArrayList;
import java.util.List;

class Solution {
    
    // [x][y][0]: 기둥 존재 여부, [x][y][1]: 보 존재 여부
    private boolean[][][] map;
    private int N;

    public int[][] solution(int n, int[][] build_frame) {
        
        this.N = n;
        map = new boolean[n + 2][n + 2][2];

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];   // 0: 기둥, 1: 보
            int action = frame[3]; // 0: 삭제, 1: 설치

            if (action == 1) { // 설치
                map[x][y][type] = true;
                if (!isValid()) { // 전체 상태가 유효하지 않으면 취소
                    map[x][y][type] = false;
                }
            } else { // 삭제
                map[x][y][type] = false;
                if (!isValid()) { // 전체 상태가 유효하지 않으면 취소
                    map[x][y][type] = true;
                }
            }
        }

        // 결과를 [x, y, type] 순으로 수집 (x -> y -> type 오름차순)
        List<int[]> resultList = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (map[x][y][0]) resultList.add(new int[]{x, y, 0});
                if (map[x][y][1]) resultList.add(new int[]{x, y, 1});
            }
        }

        return resultList.toArray(new int[resultList.size()][]);
    }

    /* 모든 구조물이 현재 조건에 맞게 배치되어 있는지 검증 */
    private boolean isValid() {
        
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                // 기둥 검증
                if (map[x][y][0]) {
                    if (!canPlacePillar(x, y)) return false;
                }
                // 보 검증
                if (map[x][y][1]) {
                    if (!canPlaceBeam(x, y)) return false;
                }
            }
        }
        return true;
    }

    /* 기둥이 위치할 수 있는지 확인 */
    private boolean canPlacePillar(int x, int y) {
        
        // 1. 바닥 위
        if (y == 0) return true;
        // 2. 다른 기둥 위
        if (y > 0 && map[x][y - 1][0]) return true;
        // 3. 보의 한쪽 끝 부분 위
        if ((x > 0 && map[x - 1][y][1]) || map[x][y][1]) return true;

        return false;
    }

    /* 보가 위치할 수 있는지 확인 */
    private boolean canPlaceBeam(int x, int y) {
        
        // 1. 한쪽 끝 부분이 기둥 위 (왼쪽 끝 또는 오른쪽 끝)
        if ((y > 0 && map[x][y - 1][0]) || (y > 0 && map[x + 1][y - 1][0])) return true;
        // 2. 양쪽 끝 부분이 다른 보와 동시에 연결
        if (x > 0 && map[x - 1][y][1] && map[x + 1][y][1]) return true;

        return false;
    }
}