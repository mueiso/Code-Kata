class Solution {
    
    public String solution(String m, String[] musicinfos) {
        
        String answer = "(None)";
        int maxPlayTime = -1;
        
        // 1. 네오가 기억한 멜로디의 샵(#) 음들을 소문자로 치환합니다.
        m = replaceSharp(m);
        
        for (String info : musicinfos) {
            // 정보를 쉼표(,) 기준으로 분리
            String[] split = info.split(",");
            String startTime = split[0];
            String endTime = split[1];
            String title = split[2];
            String sheet = replaceSharp(split[3]);
            
            // 2. 재생 시간 계산 (분 단위)
            int playTime = getPlayTime(startTime, endTime);
            
            // 3. 실제 재생 시간 동안 흘러나온 전체 멜로디 생성
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < playTime; i++) {
                // 악보의 길이보다 재생 시간이 길면 처음부터 다시 반복되도록 나머지 연산(%) 활용
                sb.append(sheet.charAt(i % sheet.length()));
            }
            String playedMelody = sb.toString();
            
            // 4. 네오가 기억한 멜로디가 포함되어 있는지 검사
            if (playedMelody.contains(m)) {
                // 재생 시간이 기존 최대 시간보다 '엄격히 클 때만' 갱신
                // (이 조건 덕분에 시간이 같을 경우 먼저 입력된 곡이 유지됩니다.)
                if (playTime > maxPlayTime) {
                    maxPlayTime = playTime;
                    answer = title;
                }
            }
        }
        
        return answer;
    }
    
    /* #이 붙은 음들을 소문자 한 글자로 치환하는 헬퍼 메서드 */
    private String replaceSharp(String s) {
        
        return s.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");
    }
    
    /* HH:MM 형식의 시간 차이를 분(Minute) 단위로 계산하는 헬퍼 메서드 */
    private int getPlayTime(String start, String end) {
        
        String[] sTime = start.split(":");
        String[] eTime = end.split(":");
        
        int sMin = Integer.parseInt(sTime[0]) * 60 + Integer.parseInt(sTime[1]);
        int eMin = Integer.parseInt(eTime[0]) * 60 + Integer.parseInt(eTime[1]);
        
        return eMin - sMin;
    }
}