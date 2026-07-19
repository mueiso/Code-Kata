class Solution {
    
    public int solution(int sticker[]) {
        
        int n = sticker.length;
        
        // 예외 처리: 스티커가 1개인 경우 바로 반환
        if (n == 1) return sticker[0];
        // 예외 처리: 스티커가 2개인 경우 둘 중 큰 값 반환
        if (n == 2) return Math.max(sticker[0], sticker[1]);
        
        // --- 케이스 1: 첫 번째 스티커를 뜯는 경우 ---
        int[] dp1 = new int[n];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0]; // 첫 번째를 뜯었으므로 두 번째는 못 뜯음 (값 유지)
        
        // 마지막 스티커(n-1) 직전까지만 탐색
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }
        
        // --- 케이스 2: 첫 번째 스티커를 뜯지 않는 경우 ---
        int[] dp2 = new int[n];
        dp2[0] = 0; // 첫 번째 스티커 패스
        dp2[1] = sticker[1];
        
        // 마지막 스티커(n-1)까지 포함하여 탐색
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }
        
        // 두 케이스의 최종 결과 중 더 큰 값을 반환
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}