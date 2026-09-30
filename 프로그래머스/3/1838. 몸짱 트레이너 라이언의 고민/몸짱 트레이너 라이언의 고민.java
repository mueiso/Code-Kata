import java.util.ArrayList;
import java.util.List;

class Solution {
    
    public int solution(int n, int m, int[][] timetable) {
        
        // 1. 동시간대 최대 이용객 수(maxCustomers) 계산
        int[] timeCount = new int[1322];
        int maxCustomers = 0;

        for (int[] t : timetable) {
            for (int i = t[0]; i <= t[1]; i++) {
                timeCount[i]++;
                maxCustomers = Math.max(maxCustomers, timeCount[i]);
            }
        }

        // 손님이 1명 이하이거나 이용 시간이 한 번도 겹치지 않는 경우
        if (maxCustomers <= 1) {
            return 0;
        }

        // 2. 가능 거리를 최대 값(2n - 2)부터 1까지 줄여가며 검증
        for (int d = 2 * n - 2; d >= 1; d--) {
            if (canPlace(n, maxCustomers, d)) {
                return d;
            }
        }

        return 0;
    }

    /* 최소 거리 d 이상으로 k개의 락커를 배치할 수 있는지 확인 */
    private boolean canPlace(int n, int k, int d) {
        
        // 첫 번째 락커 위치를 (r, c)로 고정 탐색
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                List<int[]> placed = new ArrayList<>();
                placed.add(new int[]{r, c});

                // (r, c) 이후 칸들을 순서대로 탐색하며 그리디하게 배치
                for (int i = r; i < n; i++) {
                    int startC = (i == r) ? c + 1 : 0;
                    for (int j = startC; j < n; j++) {
                        boolean valid = true;
                        for (int[] p : placed) {
                            if (Math.abs(p[0] - i) + Math.abs(p[1] - j) < d) {
                                valid = false;
                                break;
                            }
                        }
                        if (valid) {
                            placed.add(new int[]{i, j});
                            if (placed.size() == k) {
                                return true;
                            }
                        }
                    }
                }

                if (placed.size() >= k) {
                    return true;
                }
            }
        }
        return false;
    }
}