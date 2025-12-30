import java.util.*;

class Solution {
    
    public int[] solution(String[] id_list, String[] report, int k) {
        
        int n = id_list.length;

        // id -> index
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) idxMap.put(id_list[i], i);

        // "신고자 -> (신고한 사람들 set)" : 중복 신고 제거용
        Map<String, Set<String>> reportedBy = new HashMap<>();
        for (String id : id_list) reportedBy.put(id, new HashSet<>());

        // report 처리: "a b" = a가 b를 신고
        for (String r : report) {
            String[] parts = r.split(" ");
            String from = parts[0];
            String to = parts[1];

            // to를 신고한 사람 목록에 from 추가 (Set이라 중복 자동 제거)
            reportedBy.get(to).add(from);
        }

        // 정지된 유저 집합(신고자 수 >= k)
        Set<String> banned = new HashSet<>();
        for (String target : id_list) {
            if (reportedBy.get(target).size() >= k) banned.add(target);
        }

        // 각 유저가 받을 메일 수 계산
        int[] answer = new int[n];
        for (String target : banned) {
            for (String reporter : reportedBy.get(target)) {
                answer[idxMap.get(reporter)]++; // 정지된 유저를 신고한 사람에게 메일 +1
            }
        }

        return answer;
    }
}