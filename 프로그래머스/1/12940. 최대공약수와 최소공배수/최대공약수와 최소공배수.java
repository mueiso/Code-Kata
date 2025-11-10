class Solution {
    
    public int[] solution(int n, int m) {
        
        int gcd = getGCD(n, m);
        int lcm = (int)(((long) n * m) / gcd); // long으로 형 변환하여 오버플로우 방지
        
        return new int[]{gcd, lcm};
    }

    // 최대공약수 계산 (유클리드 호제법)
    private int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        
        return a;
    }
}