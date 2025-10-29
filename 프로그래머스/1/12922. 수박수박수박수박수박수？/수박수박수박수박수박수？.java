public class Solution {
    
    public String solution(int n) {
        
        // "수박"을 (n / 2 + 1)번 반복하여 충분히 긴 문자열을 만든 뒤
        // substring(0, n)으로 원하는 길이만 잘라서 반환
        return "수박".repeat((n / 2) + 1).substring(0, n);
    }
}
