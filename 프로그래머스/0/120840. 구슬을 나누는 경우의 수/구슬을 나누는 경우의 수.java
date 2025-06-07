class Solution {
    // 조합 계산: nCr = n! / (r! * (n-r)!)
    public int solution(int balls, int share) {
        return combination(balls, share);
    }
    
    // 조합 계산 함수 (재귀 또는 반복으로 구현 가능)
    private int combination(int n, int r) {
        if (r == 0 || n == r) return 1;
        return combination(n - 1, r - 1) + combination(n - 1, r);
    }
}
