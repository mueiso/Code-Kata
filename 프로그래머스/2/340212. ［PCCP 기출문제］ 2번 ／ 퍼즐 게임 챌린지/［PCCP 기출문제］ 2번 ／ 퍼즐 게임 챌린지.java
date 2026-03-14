class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        int left = 1;
        int right = 0;
        
        // level의 최대값 설정
        for (int diff : diffs) {
            right = Math.max(right, diff);
        }
        
        int answer = right;
        
        // 이분 탐색으로 최소 숙련도 찾기
        while (left <= right) {
            
            int mid = (left + right) / 2;
            
            // 현재 숙련도에서 걸리는 총 시간 계산
            long totalTime = calculateTime(diffs, times, mid);
            
            if (totalTime <= limit) {
                // 제한 시간 안에 가능하면 더 작은 숙련도 탐색
                answer = mid;
                right = mid - 1;
            } else {
                // 시간 초과하면 숙련도 올리기
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    /* 특정 숙련도(level)에서 전체 퍼즐 푸는 시간 계산 */
    private long calculateTime(int[] diffs, int[] times, int level) {
        
        long total = 0;
        
        for(int i = 0; i < diffs.length; i++) {
            
            int diff = diffs[i];
            int timeCur = times[i];
            
            // 이전 퍼즐 시간
            int timePrev = 0;
            
            if (i > 0) {
                timePrev = times[i - 1];
            }
            
            if (diff <= level) {
                // 틀리지 않고 바로 해결
                total += timeCur;
            } else {
                // 틀리는 횟수
                int fail = diff - level;
                
                // (현재 퍼즐 시간 + 이전 퍼즐 시간) * 틀린 횟수 + 마지막 성공 시간
                total += (long) fail * (timeCur + timePrev) + timeCur;
            }
        }
        
        return total;
    }
}