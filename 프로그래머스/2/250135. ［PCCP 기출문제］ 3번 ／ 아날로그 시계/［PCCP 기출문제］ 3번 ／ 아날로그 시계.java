class Solution {
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        // 1. 시간을 모두 초 단위로 변환
        int start = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;
        
        // 2. 누적 알람 횟수 계산 (end 시점 - start 시점)
        // start 시점 당일에 알람이 울리는 조건(정각)이면 +1을 해줌
        return getCount(end) - getCount(start) + (start % 3600 == 0 ? 1 : 0);
    }
    
    /* 0시 0분 0초 (초과) ~ t초 (이하)까지 알람이 울린 총 횟수를 구하는 함수 */
    private int getCount(int t) {
        
        // 초침이 분침을 지나친 횟수
        int mCount = t * 59 / 3600;
        
        // 초침이 시침을 지나친 횟수
        int hCount = t * 719 / 43200;
        
        // 12시 정각(43200초)에는 3개의 바늘이 모두 겹쳐서 한 번만 울려야 하므로 중복된 1회를 빼줌
        // (입력값이 23:59:59 까지이므로 중복은 최대 1번만 발생함)
        int duplicates = t >= 43200 ? 1 : 0;
        
        return mCount + hCount - duplicates;
    }
}