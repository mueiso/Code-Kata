class Solution {
    
    public int solution(int n, int[][] computers) {
        
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            // 아직 탐색하지 않은 컴퓨터가 있다면 새로운 네트워크 시작
            if (!visited[i]) {
                dfs(i, computers, visited);
                answer++; // 네트워크 개수 증가
            }
        }

        return answer;
    }

    private void dfs(int node, int[][] computers, boolean[] visited) {
        
        visited[node] = true; // 현재 컴퓨터 방문 처리

        for (int i = 0; i < computers.length; i++) {
            // 자기 자신이 아니고, 연결되어 있으며, 아직 방문하지 않은 컴퓨터로 이동
            if (computers[node][i] == 1 && !visited[i]) {
                dfs(i, computers, visited);
            }
        }
    }
}