class Solution {
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        
        int[] answer = new int[balls.length];
        
        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            
            int minDistanceSq = Integer.MAX_VALUE;
            
            // 1. 위쪽 벽을 맞는 경우 (y = n에 대칭)
            // 제외 조건: x좌표가 같고 목표 공이 더 위에 있는 경우 (벽보다 공에 먼저 맞음)
            if (!(startX == targetX && startY < targetY)) {
                int distSq = (startX - targetX) * (startX - targetX)
                    + (startY - (2 * n - targetY)) * (startY - (2 * n - targetY));
                
                minDistanceSq = Math.min(minDistanceSq, distSq);
            }
            
            // 2. 아래쪽 벽을 맞는 경우 (y = 0에 대칭)
            // 제외 조건: x좌표가 같고 목표 공이 더 아래에 있는 경우
            if (!(startX == targetX && startY > targetY)) {
                int distSq = (startX - targetX) * (startX - targetX)
                    + (startY - (-targetY)) * (startY - (-targetY));
                
                minDistanceSq = Math.min(minDistanceSq, distSq);
            }
            
            // 3. 왼쪽 벽을 맞는 경우 (x = 0에 대칭)
            // 제외 조건: y좌표가 같고 목표 공이 더 왼쪽에 있는 경우
            if (!(startY == targetY && startX > targetX)) {
                int distSq = (startX - (-targetX)) * (startX - (-targetX))
                    + (startY - targetY) * (startY - targetY);
                
                minDistanceSq = Math.min(minDistanceSq, distSq);
            }
            
            // 4. 오른쪽 벽을 맞는 경우 (x = m에 대칭)
            // 제외 조건: y좌표가 같고 목표 공이 더 오른쪽에 있는 경우
            if (!(startY == targetY && startX < targetX)) {
                int distSq = (startX - (2 * m - targetX)) * (startX - (2 * m - targetX))
                    + (startY - targetY) * (startY - targetY);
                
                minDistanceSq = Math.min(minDistanceSq, distSq);
            }
            
            // 최솟값 배열에 저장
            answer[i] = minDistanceSq;
        }
        
        return answer;
    }
}