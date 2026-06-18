class Solution {
    
    public int[] solution(long begin, long end) {
        
        int size = (int) (end - begin + 1);
        int[] answer = new int[size];
        
        for (int i = 0; i < size; i++) {
            long current = begin + i;
            answer[i] = getBlock(current);
        }
        
        return answer;
    }
    
    private int getBlock(long n) {
        
        // 1번 위치에는 어떤 블록도 놓일 수 없음
        if (n == 1) return 0;
        
        long max_div = 1; // 기본적으로 1번 블록은 모든 곳의 약수이므로 1로 초기화
        
        // 정수론에 따라 소수/약수 판별은 제곱근까지만 수행해도 충분합니다.
        for (long k = 2; k * k <= n; k++) {
            if (n % k == 0) {
                // n/k는 자기 자신을 제외한 가장 큰 약수 후보
                if (n / k <= 10000000) {
                    return (int) (n / k); // 1,000만 이하라면 이보다 큰 블록은 없으므로 즉시 반환
                }
                // n/k가 1,000만을 넘어가면 쓸 수 없으므로, 작은 약수인 k를 저장해 둡니다.
                // k는 루프를 돌수록 커지므로 별도의 Math.max 없이 갱신해 주면 됩니다.
                max_div = k;
            }
        }
        
        return (int) max_div;
    }
}