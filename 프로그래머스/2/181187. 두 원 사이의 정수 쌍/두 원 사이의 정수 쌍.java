class Solution {
    
    public long solution(int r1, int r2) {
        
        long answer = 0;
        
        // x를 1부터 r2까지 탐색 (1사분면 기준)
        for (long x = 1; x <= r2; x++) {
            
            // 큰 원에서 가능한 최대 y
            long yMax = (long) Math.sqrt((long) r2 * r2 - x * x);
            
            long yMin = 0;
            
            // 작은 원 내부를 제외하기 위해 최소 y 계산
            if (x < r1) {
                yMin = (long) Math.ceil(Math.sqrt((long) r1 * r1 - x * x));
            }
            
            // 해당 x에서 가능한 y 개수
            answer += (yMax - yMin + 1);
        }
        
        // 4개의 사분면 대칭
        return answer * 4;
    }
}