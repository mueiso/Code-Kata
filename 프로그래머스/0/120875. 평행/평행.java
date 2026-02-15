/*
 * 점이 4개일 때 두 선분을 만드는 경우는:
   
   1. (0-1) & (2-3)
   2. (0-2) & (1-3)
   3. (0-3) & (1-2)

 * 이 중 하나라도 기울기가 같으면 정답은 1
 */

class Solution {
    
    public int solution(int[][] dots) {

        // 경우 1: (0-1) 와 (2-3)
        if (isParallel(dots[0], dots[1], dots[2], dots[3])) return 1;

        // 경우 2: (0-2) 와 (1-3)
        if (isParallel(dots[0], dots[2], dots[1], dots[3])) return 1;

        // 경우 3: (0-3) 와 (1-2)
        if (isParallel(dots[0], dots[3], dots[1], dots[2])) return 1;

        return 0;
    }

    // 두 선분이 평행한지 확인
    private boolean isParallel(int[] a, int[] b, int[] c, int[] d) {
        
        int dx1 = b[0] - a[0];
        int dy1 = b[1] - a[1];

        int dx2 = d[0] - c[0];
        int dy2 = d[1] - c[1];

        // 교차 곱 비교 (기울기 비교와 동일)
        return dy1 * dx2 == dy2 * dx1;
    }
}