class Solution {
    public int solution(int n) {
        
        for (int x = 2; x < n; x++) {
            if (n % x == 1) {
                return x; // 첫 번째로 만족하는 x를 찾으면 바로 리턴
            }
        }
        return n - 1;
    }
}