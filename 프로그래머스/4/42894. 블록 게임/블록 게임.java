import java.util.*;

class Solution {
    
    public int solution(int[][] board) {
        
        int n = board.length;
        int removedCount = 0;
        boolean updated = true;

        // 더 이상 제거할 수 있는 블록이 없을 때까지 반복
        while (updated) {
            updated = false;

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    // 1. 2 x 3 영역 체크
                    if (r + 1 < n && c + 2 < n) {
                        if (tryRemove(board, r, c, 2, 3)) {
                            removedCount++;
                            updated = true;
                            break;
                        }
                    }
                    // 2. 3 x 2 영역 체크
                    if (r + 2 < n && c + 1 < n) {
                        if (tryRemove(board, r, c, 3, 2)) {
                            removedCount++;
                            updated = true;
                            break;
                        }
                    }
                }
                if (updated) break;
            }
        }

        return removedCount;
    }

    /* 특정 영역이 정확히 4칸의 블록과 2칸의 빈 칸으로 이루어져 채워질 수 있는지 확인하고 제거하는 메서드 */
    private boolean tryRemove(int[][] board, int r, int c, int h, int w) {
        
        int blockId = -1;
        List<int[]> emptyCells = new ArrayList<>();

        // 영역 내 블록 ID 및 빈 칸(0) 위치 파악
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                } else {
                    if (blockId == -1) {
                        blockId = board[i][j];
                    } else if (blockId != board[i][j]) {
                        return false; // 서로 다른 블록이 섞여있으면 불가능
                    }
                }
            }
        }

        // 블록이 존재하지 않거나, 빈 칸이 정확히 2개가 아니라면 올바른 블록 모양이 아님
        if (blockId == -1 || emptyCells.size() != 2) return false;

        // 빈 칸의 위쪽에 가로막는 다른 블록이 있는지 확인 (검은 블록 낙하 가능 여부)
        for (int[] cell : emptyCells) {
            int er = cell[0];
            int ec = cell[1];
            for (int i = 0; i < er; i++) {
                if (board[i][ec] != 0) {
                    return false; // 위에 다른 블록이 가로막고 있음
                }
            }
        }

        // 조건을 만족하므로 해당 블록을 보드에서 제거 (0으로 초기화)
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                if (board[i][j] == blockId) {
                    board[i][j] = 0;
                }
            }
        }

        return true;
    }
}