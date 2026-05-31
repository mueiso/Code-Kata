import java.util.*;

class Solution {
    
    public int solution(String[][] relation) {
        
        int rowLen = relation.length;
        int colLen = relation[0].length;
        List<Integer> candidateKeys = new ArrayList<>();

        // 1. 모든 속성 조합을 비트마스크로 탐색 (1부터 2^colLen - 1까지)
        for (int i = 1; i < (1 << colLen); i++) {
            // 2. 유일성 검사
            if (isUnique(i, relation, rowLen, colLen)) {
                // 3. 최소성 검사
                if (isMinimal(i, candidateKeys)) {
                    candidateKeys.add(i);
                }
            }
        }

        return candidateKeys.size();
    }

    private boolean isUnique(int mask, String[][] relation, int rowLen, int colLen) {
        
        Set<String> set = new HashSet<>();
        
        for (int r = 0; r < rowLen; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < colLen; c++) {
                // mask의 c번째 비트가 1이면 해당 컬럼 선택
                if ((mask & (1 << c)) != 0) {
                    sb.append(relation[r][c]).append("/"); // 구분자 추가로 데이터 혼동 방지
                }
            }
            set.add(sb.toString());
        }
        
        // 중복 제거 후 크기가 전체 행 개수와 같으면 유일성 만족
        return set.size() == rowLen;
    }

    private boolean isMinimal(int mask, List<Integer> candidateKeys) {
        
        for (int key : candidateKeys) {
            // (현재 조합 & 기존 후보키) == 기존 후보키 라면 현재 조합은 최소성 위배
            if ((mask & key) == key) {
                
                return false;
            }
            
        }
        return true;
    }
}