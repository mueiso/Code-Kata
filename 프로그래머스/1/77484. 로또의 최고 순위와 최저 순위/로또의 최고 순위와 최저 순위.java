import java.util.*;

class Solution {
    
    public int[] solution(int[] lottos, int[] win_nums) {
        
        int zeroCount = 0;   // 알아볼 수 없는 번호 개수 (0의 개수)
        int matchCount = 0;  // 실제로 일치하는 번호 개수
        
        // 당첨 번호를 빠르게 찾기 위해 Set 사용
        Set<Integer> winSet = new HashSet<>();
        for (int num : win_nums) {
            winSet.add(num);
        }
        
        for (int num : lottos) {
            if (num == 0) {
                zeroCount++;              // 0이면 모르는 번호
            } else if (winSet.contains(num)) {
                matchCount++;             // 당첨 번호와 일치
            }
        }
        
        // 최대로 맞출 수 있는 개수 = 현재 일치 + 0 개수
        int maxMatch = matchCount + zeroCount;
        // 최소로 맞출 수 있는 개수 = 현재 일치 개수 그대로
        int minMatch = matchCount;
        
        int bestRank = getRank(maxMatch);
        int worstRank = getRank(minMatch);
        
        return new int[] {bestRank, worstRank};
    }
    
    // 맞춘 개수 → 순위로 변환
    private int getRank(int match) {
        if (match >= 2) {
            return 7 - match;
        } else {
            return 6;
        }
    }
}