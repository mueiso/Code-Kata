class Solution {
    
    public int solution(int n, int[][] q, int[] ans) {
        
        // 백트래킹을 통해 1부터 n까지 숫자 중 5개를 뽑는 조합 탐색을 시작한다.
        return dfs(1, 0, new int[5], n, q, ans);
    }

    /*
     * 1부터 n까지의 숫자 중 5개를 선택하는 모든 조합을 생성하는 백트래킹(DFS) 메서드다.
     * 오름차순으로 숫자를 선택하여 중복된 조합이 생성되는 것을 방지한다.
     * 5개의 숫자가 모두 선택되면(depth == 5) 해당 조합이 
     * 입력된 시도(q)와 시스템 응답(ans) 조건에 모두 일치하는지 검증한다.
     * 모든 시나리오를 통과한 올바른 비밀 코드 조합의 개수를 누적하여 반환한다.
     */
    private int dfs(int start, int depth, int[] comb, int n, int[][] q, int[] ans) {
        
        // 5개의 숫자를 모두 뽑은 경우
        if (depth == 5) {
            
            // 현재 생성된 조합(comb)이 모든 시스템 응답(ans)을 만족하는지 확인한다.
            if (isValid(comb, q, ans)) {
                
                return 1; // 조건에 맞으면 1을 반환하여 카운트를 증가시킨다.
            }
            
            return 0;     // 조건에 맞지 않으면 0을 반환한다.
        }

        int count = 0;
        // start부터 n까지 숫자를 순차적으로 선택한다 (오름차순 유지).
        for (int i = start; i <= n; i++) {
            comb[depth] = i;
            count += dfs(i + 1, depth + 1, comb, n, q, ans);
        }
        
        return count;
    }

    /*
     * 만들어진 조합이 모든 시도(q)와 응답(ans)을 만족하는지 검사하는 메서드다.
     */
    private boolean isValid(int[] comb, int[][] q, int[] ans) {
        
        for (int i = 0; i < q.length; i++) {
            
            int matchCount = 0;
            int p1 = 0; // comb 배열의 포인터
            int p2 = 0; // q[i] 배열의 포인터
            
            // 두 배열 모두 오름차순 정렬되어 있으므로 투 포인터로 빠르게 교집합 개수를 탐색한다.
            while (p1 < 5 && p2 < 5) {
                
                if (comb[p1] == q[i][p2]) {
                    
                    matchCount++;
                    p1++;
                    p2++;
                } else if (comb[p1] < q[i][p2]) {
                    p1++;
                } else {
                    p2++;
                }
            }
            
            // 시스템이 응답한 일치 개수와 다르면 올바른 비밀 코드가 아니다.
            if (matchCount != ans[i]) {
                
                return false; 
            }
        }
        
        // 모든 시도에 대해 조건을 만족하면 true를 반환한다.
        return true; 
    }
}