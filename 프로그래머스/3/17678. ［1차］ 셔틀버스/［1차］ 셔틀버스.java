import java.util.Arrays;

class Solution {
    
    public String solution(int n, int t, int m, String[] timetable) {
        
        // 1. 크루도착 시각을 분(minute) 단위 정수로 변환 후 정렬
        int[] crewTimes = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            crewTimes[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }
        Arrays.sort(crewTimes);

        int crewIndex = 0;
        int shuttleTime = 540; // 09:00 (9 * 60)
        int lastCrewTime = 0;
        int boardedCount = 0;

        // 2. n회 셔틀 운행 시뮬레이션
        for (int i = 0; i < n; i++) {
            boardedCount = 0;

            // 현재 셔틀 시각 이하에 도착한 크루를 최대 m명까지 태움
            while (crewIndex < crewTimes.length && crewTimes[crewIndex] <= shuttleTime && boardedCount < m) {
                lastCrewTime = crewTimes[crewIndex];
                crewIndex++;
                boardedCount++;
            }

            // 다음 셔틀 도착 시각 갱신
            if (i < n - 1) {
                shuttleTime += t;
            }
        }

        // 3. 콘의 최적 도착 시각 계산
        int conTime = 0;
        if (boardedCount < m) {
            // 자리가 남아있다면 셔틀 도착 시각에 정시 도착
            conTime = shuttleTime;
        } else {
            // 자리가 꽉 찼다면 마지막으로 탄 크루보다 1분 일찍 도착
            conTime = lastCrewTime - 1;
        }

        // 4. "HH:MM" 문자열 포맷으로 변환
        int hour = conTime / 60;
        int minute = conTime % 60;
        
        return String.format("%02d:%02d", hour, minute);
    }
}