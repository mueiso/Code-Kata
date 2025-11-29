class Solution {
    
    public int solution(int[] date1, int[] date2) {

        // 연도 비교
        if (date1[0] < date2[0]) return 1;
        if (date1[0] > date2[0]) return 0;

        // 월 비교
        if (date1[1] < date2[1]) return 1;
        if (date1[1] > date2[1]) return 0;

        // 일 비교
        if (date1[2] < date2[2]) return 1;
        return 0;   // 앞서지 않는 경우
    }
}