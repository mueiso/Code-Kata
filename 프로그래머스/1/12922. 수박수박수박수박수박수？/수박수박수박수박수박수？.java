public class Solution {
    public String solution(int n) {
        StringBuilder result = new StringBuilder();
        String pattern = "수박";
        
        for (int i = 0; i < n; i++) {
            result.append(pattern.charAt(i % 2)); // '수' 또는 '박'을 번갈아 추가
        }
        
        return result.toString();
    }
}
