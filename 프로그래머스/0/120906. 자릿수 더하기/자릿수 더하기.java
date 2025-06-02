class Solution {
    public int solution(int n) {
        int sum = 0;
        
        while (n > 0) {
            sum += n % 10;  // 마지막 자리 숫자 더하기
            n /= 10;        // 마지막 자리 제거
        }
        
        return sum;
    }
}
