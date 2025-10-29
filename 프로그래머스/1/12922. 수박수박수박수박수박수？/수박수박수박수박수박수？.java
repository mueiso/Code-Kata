public class Solution {
    
    public String solution(int n) {
        
        // 문자열을 효율적으로 누적하기 위한 StringBuilder 사용
        StringBuilder result = new StringBuilder();
        // 반복 패턴 문자열 ("수박")
        String pattern = "수박";

        for (int i = 0; i < n; i++) {
            // i % 2 : 짝수면 0, 홀수면 1
            // pattern.charAt(0) → '수', pattern.charAt(1) → '박'
            result.append(pattern.charAt(i % 2));   // "수박수박..." 순서대로 붙이기
        }
        
        return result.toString();
    }
}
