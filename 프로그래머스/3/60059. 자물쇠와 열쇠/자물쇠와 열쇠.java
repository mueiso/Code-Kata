class Solution {
    
    public boolean solution(int[][] key, int[][] lock) {
        
        int m = key.length;
        int n = lock.length;

        // 1. 열쇠 이동을 위해 자물쇠 영역 확장
        int newSize = n + (m - 1) * 2;

        // 2. 4가지 방향으로 회전하며 탐색
        for (int r = 0; r < 4; r++) {
            key = rotate(key);

            // 열쇠의 왼쪽 위 모서리 위치 (x, y) 이동
            for (int x = 0; x <= newSize - m; x++) {
                for (int y = 0; y <= newSize - m; y++) {

                    // 확장된 자물쇠 판 생성 및 중앙에 원래 자물쇠 배치
                    int[][] extendedLock = new int[newSize][newSize];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            extendedLock[i + m - 1][j + m - 1] = lock[i][j];
                        }
                    }

                    // 열쇠를 자물쇠에 놓기 (값 더하기)
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            extendedLock[x + i][y + j] += key[i][j];
                        }
                    }

                    // 자물쇠가 열렸는지 확인
                    if (check(extendedLock, m, n)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /* 90도 시계 방향 회전 함수 */
    private int[][] rotate(int[][] key) {
        
        int m = key.length;
        int[][] rotated = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][m - 1 - i] = key[i][j];
            }
        }
        return rotated;
    }

    /* 중앙의 N x N 자물쇠 영역이 모두 1인지 검증 */
    private boolean check(int[][] extendedLock, int m, int n) {
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (extendedLock[i + m - 1][j + m - 1] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}