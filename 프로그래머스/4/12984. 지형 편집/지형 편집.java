import java.util.*;

class Solution {
    
    public long solution(int[][] land, int P, int Q) {
        
        long minHeight = Long.MAX_VALUE;
        long maxHeight = 0;

        // 지형의 최소 높이와 최대 높이 탐색
        for (int[] row : land) {
            for (int val : row) {
                minHeight = Math.min(minHeight, val);
                maxHeight = Math.max(maxHeight, val);
            }
        }

        long low = minHeight;
        long high = maxHeight;
        long minCost = Long.MAX_VALUE;

        // 삼분 탐색 (Ternary Search)
        while (high - low >= 3) {
            long m1 = low + (high - low) / 3;
            long m2 = high - (high - low) / 3;

            long cost1 = calculateCost(land, m1, P, Q);
            long cost2 = calculateCost(land, m2, P, Q);

            minCost = Math.min(minCost, Math.min(cost1, cost2));

            if (cost1 < cost2) {
                high = m2; // 최솟값이 왼쪽에 위치
            } else {
                low = m1;  // 최솟값이 오른쪽에 위치
            }
        }

        // 남은 좁은 범위에서 완전탐색으로 정확한 최솟값 확정
        for (long h = low; h <= high; h++) {
            minCost = Math.min(minCost, calculateCost(land, h, P, Q));
        }

        return minCost;
    }

    /* 특정 높이 H로 맞출 때 드는 총 비용 계산 메서드 */
    private long calculateCost(int[][] land, long H, int P, int Q) {
        
        long cost = 0;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] < H) {
                    cost += (H - land[i][j]) * P; // 부족한 만큼 추가
                } else if (land[i][j] > H) {
                    cost += (land[i][j] - H) * Q; // 초과한 만큼 제거
                }
            }
        }
        return cost;
    }
}