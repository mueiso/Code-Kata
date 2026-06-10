class Solution {
    
    private int[][] answer;
    private int index = 0;

    public int[][] solution(int n) {
        
        // 하노이의 탑 총 이동 횟수는 2^n - 1 번입니다.
        int totalMoves = (int) Math.pow(2, n) - 1;
        answer = new int[totalMoves][2];
        
        // 1번 기둥에서 3번 기둥으로, 2번 기둥을 거쳐서 n개의 원판을 이동
        hanoi(n, 1, 3, 2);
        
        return answer;
    }

    private void hanoi(int n, int from, int to, int aux) {
        
        // 기저 조건: 원판이 1개만 남았다면 바로 목적지로 이동
        if (n == 1) {
            answer[index++] = new int[]{from, to};
            return;
        }

        // STEP 1: n-1개의 원판을 시작지(from)에서 경유지(aux)로 이동 (목적지를 거쳐서)
        hanoi(n - 1, from, aux, to);
        
        // STEP 2: 가장 큰 원판을 목적지(to)로 이동
        answer[index++] = new int[]{from, to};
        
        // STEP 3: 경유지(aux)에 있던 n-1개의 원판을 목적지(to)로 이동 (시작지를 거쳐서)
        hanoi(n - 1, aux, to, from);
    }
}