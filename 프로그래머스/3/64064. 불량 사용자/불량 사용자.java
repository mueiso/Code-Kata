import java.util.HashSet;
import java.util.Set;

class Solution {
    
    private String[] uId;
    private String[] bId;
    private Set<Integer> resultSet;

    public int solution(String[] user_id, String[] banned_id) {
        
        uId = user_id;
        bId = banned_id;
        resultSet = new HashSet<>();

        // DFS 탐색 시작 (banned_id의 index: 0, initial bitmask: 0)
        dfs(0, 0);

        return resultSet.size();
    }

    private void dfs(int depth, int mask) {
        
        // 모든 banned_id 패턴 매칭을 완료한 경우
        if (depth == bId.length) {
            resultSet.add(mask);
            return;
        }

        for (int i = 0; i < uId.length; i++) {
            // i번째 user_id가 아직 선택되지 않았고, 현재 banned_id 패턴과 일치하는 경우
            if ((mask & (1 << i)) == 0 && isMatch(uId[i], bId[depth])) {
                dfs(depth + 1, mask | (1 << i));
            }
        }
    }

    private boolean isMatch(String userId, String bannedId) {
        
        if (userId.length() != bannedId.length()) {
            return false;
        }

        for (int i = 0; i < userId.length(); i++) {
            char bChar = bannedId.charAt(i);
            if (bChar != '*' && bChar != userId.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}