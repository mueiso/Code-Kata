class Solution {
    
    private int minFriends;
    private int[] extendedWeak;
    private int L;
    private int[] dist;

    public int solution(int n, int[] weak, int[] dist) {
        
        this.L = weak.length;
        this.dist = dist;
        this.minFriends = dist.length + 1; // 최댓값+1로 초기화

        // 1. 원형 배열을 일직선(2배)으로 확장
        extendedWeak = new int[L * 2];
        for (int i = 0; i < L; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + L] = weak[i] + n;
        }

        // 2. 친구 순열 생성 및 탐색
        boolean[] visited = new boolean[dist.length];
        int[] perm = new int[dist.length];
        generatePermutations(0, visited, perm);

        // 모든 친구를 투입해도 불가능한 경우 -1 반환
        return minFriends > dist.length ? -1 : minFriends;
    }

    /* 친구 이동 거리의 순열을 생성하는 DFS */
    private void generatePermutations(int depth, boolean[] visited, int[] perm) {
        
        if (depth == dist.length) {
            checkCoverage(perm);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = dist[i];
                generatePermutations(depth + 1, visited, perm);
                visited[i] = false;
            }
        }
    }

    /* 현재 친구 순서(perm)로 모든 취약 지점을 커버할 수 있는지 확인 */
    private void checkCoverage(int[] perm) {
        
        // 각 취약 지점(start)을 첫 출발점으로 설정
        for (int start = 0; start < L; start++) {
            int friendCount = 1;
            // 첫 번째 친구가 커버 가능한 마지막 위치
            int position = extendedWeak[start] + perm[0];

            // start부터 시작하여 L개의 취약 지점을 확인
            for (int i = start; i < start + L; i++) {
                // 현재 친구의 커버 범위를 벗어난 취약 지점을 만난 경우
                if (extendedWeak[i] > position) {
                    friendCount++;
                    // 준비된 친구 수를 초과하면 탐색 중단
                    if (friendCount > perm.length) {
                        break;
                    }
                    // 다음 친구 투입 및 커버 가능 위치 갱신
                    position = extendedWeak[i] + perm[friendCount - 1];
                }
            }

            minFriends = Math.min(minFriends, friendCount);
        }
    }
}