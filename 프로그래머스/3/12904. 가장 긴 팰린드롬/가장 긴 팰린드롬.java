class Solution {
    
    public int solution(String s) {
        
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            // 1. 홀수 길이 팰린드롬 검사 (i번째 글자가 정중앙일 때)
            int len1 = expandAroundCenter(s, i, i);
            
            // 2. 짝수 길이 팰린드롬 검사 (i번째와 i+1번째 글자 사이가 중앙일 때)
            int len2 = expandAroundCenter(s, i, i + 1);

            // 두 경우 중 더 큰 값으로 최댓값을 갱신합니다.
            maxLen = Math.max(maxLen, Math.max(len1, len2));
        }

        return maxLen;
    }

    /* 지정된 좌우 포인터에서 시작해 바깥으로 확장하며 팰린드롬 길이를 구하는 헬퍼 메서드 */
    private int expandAroundCenter(String s, int left, int right) {
        
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;  // 왼쪽으로 한 칸 확장
            right++; // 오른쪽으로 한 칸 확장
        }
        
        // while 루프를 탈출했을 때는 이미 대칭이 깨진 상태(또는 범위를 벗어난 상태)입니다.
        // 따라서 직전 단계의 유효했던 길이를 계산식으로 환산하면 'right - left - 1'이 됩니다.
        return right - left - 1;
    }
}