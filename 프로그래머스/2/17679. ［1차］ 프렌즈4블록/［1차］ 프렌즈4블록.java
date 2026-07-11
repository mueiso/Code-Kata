class Solution {
    
    public int solution(int m, int n, String[] board) {
        
        int answer = 0;
        
        // 1. 조작이 편하도록 String 배열을 2차원 char 배열로 변환합니다.
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        // 더 이상 지워질 블록이 없을 때까지 무한 반복
        while (true) {
            boolean[][] isTarget = new boolean[m][n];
            boolean hasMatched = false;
            
            // 2. 2x2 크기의 매칭 블록 탐색 (범위를 벗어나지 않도록 m-1, n-1까지만)
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char current = map[i][j];
                    
                    // 빈 공간('.')이 아니고, 2x2 영역의 4개 블록이 모두 같은 문자라면
                    if (current != '.' && 
                        current == map[i + 1][j] && 
                        current == map[i][j + 1] && 
                        current == map[i + 1][j + 1]) {
                        
                        isTarget[i][j] = true;
                        isTarget[i + 1][j] = true;
                        isTarget[i][j + 1] = true;
                        isTarget[i + 1][j + 1] = true;
                        hasMatched = true; // 이번 턴에 지워질 블록이 있음을 기록
                    }
                }
            }
            
            // 이번 턴에 매칭된 블록이 단 하나도 없다면 게임 종료
            if (!hasMatched) {
                break;
            }
            
            // 3. 마킹된 블록을 지우고 카운트 증가
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isTarget[i][j]) {
                        answer++;
                        map[i][j] = '.'; // 빈 공간은 '.'으로 표시
                    }
                }
            }
            
            // 4. 중력 작용: 블록들을 아래로 떨어뜨리기 (열 단위로 수행)
            for (int j = 0; j < n; j++) {
                // 맨 아래 행부터 위로 올라가며 탐색
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] == '.') {
                        // 현재 칸이 빈칸이면, 바로 위의 행(k) 중에서 가장 먼저 만나는 블록을 찾음
                        for (int k = i - 1; k >= 0; k--) {
                            if (map[k][j] != '.') {
                                map[i][j] = map[k][j]; // 아래로 복사
                                map[k][j] = '.';       // 윗자리는 빈칸으로 변경
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}