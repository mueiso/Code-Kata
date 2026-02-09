import java.util.*;

class Solution {
    
    int rows, cols;
    boolean[][] visited;
    String[] maps;

    public int[] solution(String[] maps) {
        
        this.maps = maps;
        rows = maps.length;
        cols = maps[0].length();
        visited = new boolean[rows][cols];

        List<Integer> result = new ArrayList<>();

        // 모든 칸을 순회하며 섬 탐색
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 숫자이면서 아직 방문하지 않은 경우 섬 시작
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    int sum = dfs(i, j);
                    result.add(sum);
                }
            }
        }

        // 섬이 없으면 -1 반환
        if (result.isEmpty()) {
            return new int[]{-1};
        }

        // 오름차순 정렬 후 배열 변환
        Collections.sort(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // DFS로 하나의 섬 탐색
    private int dfs(int x, int y) {
        visited[x][y] = true;
        int sum = maps[x].charAt(y) - '0'; // 현재 칸 식량

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 내 + 방문 안 함 + 바다가 아님
            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                if (!visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    sum += dfs(nx, ny);
                }
            }
        }

        return sum;
    }
}