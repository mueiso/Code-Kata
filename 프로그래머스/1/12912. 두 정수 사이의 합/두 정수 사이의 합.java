class Solution {

    public long solution(int a, int b) {
        
        // a, b 중 작은 값이 시작점, 큰 값이 끝점이 되도록 설정
        return sumAtoB(Math.min(a, b), Math.max(b, a));
    }

    private long sumAtoB(long a, long b) {
        
        // 가우스의 합 공식: (항의 개수) × (첫항 + 끝항) ÷ 2
        // 항의 개수 = (b - a + 1)
        return (b - a + 1) * (a + b) / 2;
    }
}
