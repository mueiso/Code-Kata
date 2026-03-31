/*
 * 희망 시간 + 10분 → limit
 * 7일 돌면서 평일만 체크
 * 하루라도 늦으면 탈락
 * 끝까지 통과하면 카운트
 */

class Solution {
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        int n = schedules.length;
        int answer = 0;

        // 각 직원별로 검사
        for (int i = 0; i < n; i++) {

            int limit = addTenMinutes(schedules[i]); // 출근 인정 마감 시간 (희망 +10분)
            boolean success = true; // 해당 직원이 상품 받을 수 있는지 여부

            // 일주일(7일) 출근 기록 확인
            for (int j = 0; j < 7; j++) {

                // 현재 요일 계산 (1:월 ~ 7:일)
                int day = (startday + j - 1) % 7 + 1;

                // 주말(토:6, 일:7)은 검사 제외
                if (day == 6 || day == 7) continue;

                // 해당 날 출근 시간이 마감 시간보다 늦으면 실패
                if (timelogs[i][j] > limit) {
                    success = false;
                    break; // 더 볼 필요 없음
                }
            }

            // 평일 모두 통과한 경우만 카운트
            if (success) answer++;
        }

        return answer;
    }

    /* "HHMM" 형태의 시간에 10분 더하는 함수 */
    private int addTenMinutes(int time) {
        
        int h = time / 100; // 시
        int m = time % 100; // 분

        m += 10; // 10분 추가

        // 분이 60 이상이면 시간 올림 처리
        if (m >= 60) {
            h++;
            m -= 60;
        }

        // 다시 HHMM 형태로 반환
        return h * 100 + m;
    }
}