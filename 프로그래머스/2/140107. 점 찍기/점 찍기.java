class Solution {
    
    public long solution(int k, int d) {
        
        long answer = 0;

        // x를 k 간격으로 증가
        for (long x = 0; x <= d; x += k) {
            
            // 해당 x에서 가능한 최대 y
            long maxY = (long) Math.sqrt((long)d * d - x * x);
            
            // y도 k 간격 → 개수 계산
            answer += (maxY / k) + 1;
        }

        return answer;
    }
}