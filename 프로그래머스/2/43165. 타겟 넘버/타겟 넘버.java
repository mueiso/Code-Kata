class Solution {

    int answer = 0;

    public int solution(int[] numbers, int target) {
        
        // DFS 시작: 인덱스 0, 현재 합 0
        dfs(numbers, target, 0, 0);
        
        return answer;
    }

    // DFS로 모든 + / - 경우 탐색
    private void dfs(int[] numbers, int target, int idx, int sum) {

        // 모든 숫자를 다 사용했을 때
        if (idx == numbers.length) {
            if (sum == target) {
                answer++; // 타겟을 만들면 경우의 수 증가
            }
            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(numbers, target, idx + 1, sum + numbers[idx]);

        // 현재 숫자를 빼는 경우
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}