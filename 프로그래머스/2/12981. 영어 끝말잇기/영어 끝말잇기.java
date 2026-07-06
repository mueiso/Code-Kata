import java.util.HashSet;

class Solution {
    
    public int[] solution(int n, String[] words) {
        
        // 이미 등장한 단어들을 저장할 Set
        HashSet<String> usedWords = new HashSet<>();
        
        // 첫 번째 단어는 무조건 통과하므로 먼저 저장합니다.
        usedWords.add(words[0]);
        
        // 두 번째 단어(인덱스 1)부터 끝까지 검사합니다.
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String curr = words[i];
            
            // 조건 1: 앞사람이 말한 단어의 마지막 문자로 시작하지 않는 경우
            boolean isInvalidChain = prev.charAt(prev.length() - 1) != curr.charAt(0);
            
            // 조건 2: 이미 이전에 등장했던 단어인 경우
            boolean isDuplicate = usedWords.contains(curr);
            
            // 두 조건 중 하나라도 걸리면 탈락 처리
            if (isInvalidChain || isDuplicate) {
                int person = (i % n) + 1; // 탈락한 사람의 번호
                int turn = (i / n) + 1;   // 그 사람이 맞이한 차례
                
                return new int[]{person, turn};
            }
            
            // 통과했다면 사용된 단어 목록에 추가
            usedWords.add(curr);
        }

        // 끝까지 탈락자가 없었다면 [0, 0] 반환
        return new int[]{0, 0};
    }
}