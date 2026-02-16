class Solution {

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        // 1. 모든 시간을 초 단위로 변환
        int video = toSeconds(video_len);
        int current = toSeconds(pos);
        int opStart = toSeconds(op_start);
        int opEnd = toSeconds(op_end);

        for (String cmd : commands) {

            // 2. 명령 수행 전 오프닝 체크
            if (current >= opStart && current <= opEnd) {
                current = opEnd;
            }

            // 3. 명령 처리
            if (cmd.equals("prev")) {
                current = Math.max(0, current - 10);
            }

            if (cmd.equals("next")) {
                current = Math.min(video, current + 10);
            }

            // 4. 명령 수행 후 오프닝 체크
            if (current >= opStart && current <= opEnd) {
                current = opEnd;
            }
        }

        // 5. mm:ss 형식으로 변환 후 반환
        return toTimeFormat(current);
    }

    // "mm:ss" → 초 변환
    private int toSeconds(String time) {
        
        String[] parts = time.split(":");
        int m = Integer.parseInt(parts[0]);
        int s = Integer.parseInt(parts[1]);
        
        return m * 60 + s;
    }

    // 초 → "mm:ss" 변환
    private String toTimeFormat(int seconds) {
        
        int m = seconds / 60;
        int s = seconds % 60;
        
        return String.format("%02d:%02d", m, s);
    }
}