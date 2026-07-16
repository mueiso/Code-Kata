class Solution {
    
    private int answer;
    private final char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    public int solution(int n, String[] data) {
        
        // [필수] 테스트 케이스마다 정답 카운트를 반드시 초기화해주어야 합니다.
        answer = 0; 
        
        boolean[] visited = new boolean[8];
        char[] output = new char[8];
        
        dfs(0, output, visited, data);
        
        return answer;
    }
    
    /* 8명의 모든 순열을 만드는 DFS 함수 */
    private void dfs(int depth, char[] output, boolean[] visited, String[] data) {
        
        if (depth == 8) {
            // 8명이 모두 줄을 섰다면 조건에 부합하는지 검사
            if (checkConditions(output, data)) {
                answer++;
            }
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = friends[i];
                dfs(depth + 1, output, visited, data);
                visited[i] = false;
            }
        }
    }
    
    /* 생성된 순열이 모든 조건을 만족하는지 검증하는 함수 */
    private boolean checkConditions(char[] output, String[] data) {
        
        // 빠른 위치 확인을 위해 캐릭터별 인덱스를 알파벳 순서대로 매핑하여 저장
        int[] pos = new int[26];
        for (int i = 0; i < 8; i++) {
            pos[output[i] - 'A'] = i;
        }
        
        for (String cond : data) {
            int p1 = pos[cond.charAt(0) - 'A']; // 조건 제시자 위치
            int p2 = pos[cond.charAt(2) - 'A']; // 상대방 위치
            char op = cond.charAt(3);           // 비교 연산자 (=, <, >)
            int val = cond.charAt(4) - '0';     // 요구하는 간격
            
            int dist = Math.abs(p1 - p2) - 1;   // 실제 두 사람 사이의 간격
            
            if (op == '=') {
                if (dist != val) return false;
            } else if (op == '<') {
                if (dist >= val) return false;
            } else if (op == '>') {
                if (dist <= val) return false;
            }
        }
        
        return true;
    }
}