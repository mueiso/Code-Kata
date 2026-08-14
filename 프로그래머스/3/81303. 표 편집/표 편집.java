import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    
    public String solution(int n, int k, String[] cmd) {
        
        // 1. 양방향 연결 리스트 배열 초기화
        int[] prev = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1; // 마지막 행의 next는 -1 (끝을 의미)

        // 삭제된 행 번호를 저장할 스택
        Deque<Integer> deletedStack = new ArrayDeque<>();

        // 2. 명령어 순차 처리
        for (String c : cmd) {
            char op = c.charAt(0);

            if (op == 'U') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) {
                    k = prev[k];
                }
            } else if (op == 'D') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) {
                    k = next[k];
                }
            } else if (op == 'C') {
                deletedStack.push(k);

                // 현재 행 연결 끊기
                if (prev[k] != -1) {
                    next[prev[k]] = next[k];
                }
                if (next[k] != -1) {
                    prev[next[k]] = prev[k];
                }

                // 다음 선택 행 결정
                if (next[k] != -1) {
                    k = next[k];
                } else {
                    k = prev[k];
                }
            } else if (op == 'Z') {
                int restore = deletedStack.pop();

                // 삭제됐던 행 다시 연결 복원
                if (prev[restore] != -1) {
                    next[prev[restore]] = restore;
                }
                if (next[restore] != -1) {
                    prev[next[restore]] = restore;
                }
            }
        }

        // 3. 최종 결과 문자열 생성
        char[] answer = new char[n];
        Arrays.fill(answer, 'O');

        while (!deletedStack.isEmpty()) {
            answer[deletedStack.pop()] = 'X';
        }

        return new String(answer);
    }
}