class Solution {
    
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int playSec = timeToSec(play_time);
        int advSec = timeToSec(adv_time);

        long[] totalTime = new long[playSec + 1];

        // 1. 시청 기록 Imos 알고리즘 적용
        for (String log : logs) {
            String[] parts = log.split("-");
            int start = timeToSec(parts[0]);
            int end = timeToSec(parts[1]);

            totalTime[start]++;
            totalTime[end]--;
        }

        // 2. 1차 누적 합: 각 초당 동시 시청자 수 구하기
        for (int i = 1; i <= playSec; i++) {
            totalTime[i] += totalTime[i - 1];
        }

        // 3. 2차 누적 합: 0초부터 i초까지의 총 누적 시청 시간 구하기
        for (int i = 1; i <= playSec; i++) {
            totalTime[i] += totalTime[i - 1];
        }

        // 4. 슬라이딩 윈도우로 최대 시청 시간 구간 탐색
        long maxTime = totalTime[advSec - 1]; // 0초부터 시작했을 때의 누적 시청 시간
        int maxStart = 0;

        for (int i = advSec; i < playSec; i++) {
            long currentSum = totalTime[i] - totalTime[i - advSec];
            if (currentSum > maxTime) {
                maxTime = currentSum;
                maxStart = i - advSec + 1; // 최고 기록 갱신 시 시작 시각 기록
            }
        }

        return secToTime(maxStart);
    }

    /* "HH:MM:SS" -> 초(second) 변환 */
    private int timeToSec(String time) {
        
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        
        return h * 3600 + m * 60 + s;
    }

    /* 초(second) -> "HH:MM:SS" 변환 */
    private String secToTime(int sec) {
        
        int h = sec / 3600;
        int m = (sec % 3600) / 60;
        int s = sec % 60;
        
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}