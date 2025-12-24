import java.util.*;

class Solution {
    
    public int[] solution(String today, String[] terms, String[] privacies) {

        // 약관 타입 -> 유효기간(개월)
        Map<String, Integer> termMap = new HashMap<>();
        for (String t : terms) {
            String[] parts = t.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]));
        }

        int todayDays = toDays(today);

        List<Integer> expired = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String date = parts[0];
            String type = parts[1];

            int collectedDays = toDays(date);
            int months = termMap.get(type);

            // 만료 시작일 = 수집일 + months*28
            // 오늘이 만료 시작일 이상이면 파기 대상
            int expireStartDays = collectedDays + months * 28;

            if (todayDays >= expireStartDays) {
                expired.add(i + 1); // 개인정보 번호는 1부터
            }
        }

        // List -> int[]
        int[] answer = new int[expired.size()];
        for (int i = 0; i < expired.size(); i++) {
            answer[i] = expired.get(i);
        }

        return answer;
    }

    // "YYYY.MM.DD" -> 총 일수로 변환 (모든 달 28일 가정)
    private int toDays(String date) {
        String[] d = date.split("\\.");
        int y = Integer.parseInt(d[0]);
        int m = Integer.parseInt(d[1]);
        int day = Integer.parseInt(d[2]);

        return y * 12 * 28 + m * 28 + day;
    }
}