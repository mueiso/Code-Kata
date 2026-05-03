import java.util.*;

class Solution {
    
    // 방향: 상(0), 우(1), 하(2), 좌(3) (시계방향)
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    boolean[][][] visited;
    int R, C;

    public int[] solution(String[] grid) {
        
        R = grid.length;
        C = grid[0].length();
        
        // visited[r][c][d]: (r, c) 좌표에서 d 방향으로 나가는 빛의 방문 여부
        visited = new boolean[R][C][4];
        List<Integer> cycles = new ArrayList<>();

        // 모든 좌표의 모든 방향에 대해 탐색 시작
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d]) {
                        // 방문하지 않은 경로라면 새로운 사이클을 찾아 길이 반환
                        cycles.add(simulate(grid, i, j, d));
                    }
                }
            }
        }

        // 오름차순 정렬하여 배열로 변환
        Collections.sort(cycles);
        int[] answer = new int[cycles.size()];
        for (int i = 0; i < cycles.size(); i++) {
            answer[i] = cycles.get(i);
        }
        
        return answer;
    }

    private int simulate(String[] grid, int r, int c, int d) {
        
        int length = 0;

        // 이미 방문했던 경로(사이클의 시작점 등)를 만날 때까지 반복
        while (!visited[r][c][d]) {
            visited[r][c][d] = true;
            length++;

            // 현재 방향(d)으로 한 칸 이동 (격자 끝을 넘어가면 순환되도록 처리)
            r = (r + dr[d] + R) % R;
            c = (c + dc[d] + C) % C;

            // 이동한 칸(새로운 위치)에 적힌 문자에 따라 방향 갱신
            char node = grid[r].charAt(c);
            if (node == 'L') {
                d = (d + 3) % 4; // 좌회전
            } else if (node == 'R') {
                d = (d + 1) % 4; // 우회전
            }
            // 'S'인 경우는 방향 d가 유지되므로 처리 생략
        }

        return length;
    }
}