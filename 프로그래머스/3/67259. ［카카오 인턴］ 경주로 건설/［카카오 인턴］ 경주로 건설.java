import java.util.*;

class Solution {
    
    // 이동 방향 정의: 상(0), 하(1), 좌(2), 우(3)
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int N;
    private static int[][][] minCost; // [x][y][direction]

    /* 탐색을 위한 노드 객체 */
    class Node {
        int x, y, cost, dir;

        Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir; // 0:상, 1:하, 2:좌, 3:우, -1:시작
        }
    }

    public int solution(int[][] board) {
        
        N = board.length;
        // 각 칸에 각 방향(4가지)으로 도달했을 때의 최소 비용을 저장
        minCost = new int[N][N][4];
        
        // 최소 비용 배열을 큰 값으로 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(minCost[i][j], Integer.MAX_VALUE);
            }
        }

        return bfs(board);
    }

    private int bfs(int[][] board) {
        
        Queue<Node> queue = new LinkedList<>();
        // 시작점(0,0)에서는 방향이 없으므로 -1로 설정하여 큐에 삽입
        queue.add(new Node(0, 0, 0, -1));

        int result = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            // 도착점(N-1, N-1)에 도달하면 최소 요금 갱신
            if (curr.x == N - 1 && curr.y == N - 1) {
                result = Math.min(result, curr.cost);
                continue;
            }

            // 상, 하, 좌, 우 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                // 경계 확인 및 벽(1) 여부 확인
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1) {
                    continue;
                }

                int nextCost = 0;
                // 처음 출발하거나, 이전 진행 방향과 같은 경우: 직선 도로 (100원)
                if (curr.dir == -1 || curr.dir == i) {
                    nextCost = curr.cost + 100;
                } 
                // 방향이 꺾이는 경우: 코너(500원) + 직선 도로(100원) = 600원
                else {
                    nextCost = curr.cost + 600;
                }

                // 해당 칸에 동일한 방향으로 도달한 적이 없거나, 지금 계산된 비용이 기존 저장된 최소 비용보다 작거나 같으면 갱신 후 큐에 삽입
                if (minCost[nx][ny][i] >= nextCost) {
                    minCost[nx][ny][i] = nextCost;
                    queue.add(new Node(nx, ny, nextCost, i));
                }
            }
        }
        
        return result;
    }
}