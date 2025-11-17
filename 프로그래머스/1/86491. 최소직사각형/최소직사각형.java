class Solution {
    
    public int solution(int[][] sizes) {
        
        int maxWidth = 0;   // 눕혔을 때의 '가로(짧은 변)' 중 최댓값
        int maxHeight = 0;  // 눕혔을 때의 '세로(긴 변)' 중 최댓값

        for (int[] size : sizes) {
            // 명함을 항상 짧은 변이 가로, 긴 변이 세로가 되도록 회전
            int width  = Math.min(size[0], size[1]); // 가로(짧은 변)
            int height = Math.max(size[0], size[1]); // 세로(긴 변)

            // 각 축별 최댓값 갱신
            maxWidth  = Math.max(maxWidth,  width);
            maxHeight = Math.max(maxHeight, height);
        }

        // 가장 작은 지갑의 크기 = (가로 최댓값) × (세로 최댓값)
        return maxWidth * maxHeight;
    }
}