class Solution {

    boolean[] visited; // 던전 방문 여부 체크
    int answer = 0;    // 최대 탐험 던전 수 저장

    public int solution(int k, int[][] dungeons) {
        
        visited = new boolean[dungeons.length];

        // DFS 시작 (현재 피로도 k, 탐험 수 0)
        dfs(k, dungeons, 0);

        return answer;
    }

    // DFS 탐색 함수
    private void dfs(int fatigue, int[][] dungeons, int count) {

        // 현재까지 탐험한 최대 개수 갱신
        answer = Math.max(answer, count);

        // 모든 던전 순회
        for (int i = 0; i < dungeons.length; i++) {

            // 아직 방문하지 않았고, 최소 필요 피로도 충족하면
            if (!visited[i] && fatigue >= dungeons[i][0]) {

                visited[i] = true; // 방문 처리

                // 피로도 감소 후 다음 던전 탐색
                dfs(fatigue - dungeons[i][1], dungeons, count + 1);

                visited[i] = false; // 백트래킹 (원상 복구)
            }
        }
    }
}