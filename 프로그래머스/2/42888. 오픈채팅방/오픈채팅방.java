import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public String[] solution(String[] record) {
        
        Map<String, String> userMap = new HashMap<>();
        int messageCount = 0;

        // 1차 순회: 유저 아이디별 최신 닉네임 등록 및 출력 메시지 수 계산
        for (String r : record) {
            String[] parts = r.split(" ");
            String command = parts[0];

            if (command.equals("Enter")) {
                userMap.put(parts[1], parts[2]);
                messageCount++;
            } else if (command.equals("Change")) {
                userMap.put(parts[1], parts[2]);
            } else if (command.equals("Leave")) {
                messageCount++;
            }
        }

        // 2차 순회: 최신 닉네임을 적용하여 결과 메시지 생성
        String[] answer = new String[messageCount];
        int idx = 0;

        for (String r : record) {
            String[] parts = r.split(" ");
            String command = parts[0];
            String userId = parts[1];

            if (command.equals("Enter")) {
                answer[idx++] = userMap.get(userId) + "님이 들어왔습니다.";
            } else if (command.equals("Leave")) {
                answer[idx++] = userMap.get(userId) + "님이 나갔습니다.";
            }
        }

        return answer;
    }
}