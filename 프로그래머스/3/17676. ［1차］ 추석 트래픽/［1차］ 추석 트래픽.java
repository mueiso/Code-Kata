class Solution {
    
    public int solution(String[] lines) {
        
        int n = lines.length;
        int[] startMs = new int[n];
        int[] endMs = new int[n];

        // 1. 각 로그를 밀리초(ms) 정수로 파싱
        for (int i = 0; i < n; i++) {
            String[] parts = lines[i].split(" ");

            // 응답완료시간 S (hh:mm:ss.sss)
            String[] timeParts = parts[1].split(":");
            int hh = Integer.parseInt(timeParts[0]);
            int mm = Integer.parseInt(timeParts[1]);
            double ss = Double.parseDouble(timeParts[2]);

            int end = (int) Math.round((hh * 3600 + mm * 60 + ss) * 1000);

            // 처리시간 T (0.000s)
            String tStr = parts[2].substring(0, parts[2].length() - 1);
            int duration = (int) Math.round(Double.parseDouble(tStr) * 1000);

            // 시작시간 계산 (시작과 끝을 모두 포함하므로 +1)
            int start = end - duration + 1;

            startMs[i] = start;
            endMs[i] = end;
        }

        int maxThroughput = 0;

        // 2. 각 로그의 시작 시점과 끝 시점을 기준으로 1초 창 검사
        for (int i = 0; i < n; i++) {
            // [startMs[i], startMs[i] + 999] 구간 검사
            int count1 = countRequests(startMs[i], startMs, endMs, n);
            // [endMs[i], endMs[i] + 999] 구간 검사
            int count2 = countRequests(endMs[i], startMs, endMs, n);

            maxThroughput = Math.max(maxThroughput, Math.max(count1, count2));
        }

        return maxThroughput;
    }

    /* 지정된 1초 구간(windowStart ~ windowStart + 999) 내 처리 중인 작업 수 계산 */
    private int countRequests(int windowStart, int[] startMs, int[] endMs, int n) {
        
        int windowEnd = windowStart + 999;
        int count = 0;

        for (int i = 0; i < n; i++) {
            // 작업 구간과 1초 창이 겹치는 경우
            if (startMs[i] <= windowEnd && endMs[i] >= windowStart) {
                count++;
            }
        }

        return count;
    }
}