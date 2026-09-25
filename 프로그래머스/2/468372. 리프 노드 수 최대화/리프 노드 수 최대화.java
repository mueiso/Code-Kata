class Solution {
    
    private long maxLeaves;
    private long distLimit;
    private long splitLimit;

    public int solution(int dist_limit, int split_limit) {
        
        this.distLimit = dist_limit;
        this.splitLimit = split_limit;
        this.maxLeaves = 1;

        dfs(1, 0);

        return (int) maxLeaves;
    }

    private void dfs(long P, long S) {
        
        // 1. 현재 레벨(m+1)에서 탐색을 종료하거나 부분 채움하는 경우
        maxLeaves = Math.max(maxLeaves, P);

        // 1-1. 자식 수 c = 2인 분배 노드를 R개 추가 배치
        if (P * 2 <= splitLimit) {
            long R2 = Math.max(0L, Math.min(P, distLimit - S));
            maxLeaves = Math.max(maxLeaves, P + R2);
        }

        // 1-2. 자식 수 c = 3인 분배 노드를 R개 추가 배치
        if (P * 3 <= splitLimit) {
            long R3 = Math.max(0L, Math.min(P, distLimit - S));
            maxLeaves = Math.max(maxLeaves, P + 2 * R3);
        }

        // 2. 가지치기 (Pruning): 더 깊이 들어가서 얻을 수 있는 최대 리프 수 상한 검사
        long maxPossibleFromDeeper = Math.min(splitLimit, P + 2 * (distLimit - S));
        if (maxPossibleFromDeeper <= maxLeaves) {
            return;
        }

        // 3. 레벨 m+1의 모든 노드(P개)를 분배 노드로 완전 채우고 다음 레벨로 탐색
        if (S + P <= distLimit) {
            // 이득이 더 큰 c = 3 우선 탐색
            if (P * 3 <= splitLimit) {
                dfs(P * 3, S + P);
            }
            // c = 2 탐색
            if (P * 2 <= splitLimit) {
                dfs(P * 2, S + P);
            }
        }
    }
}