import java.util.*;

class Solution {
    
    public int solution(int[] priorities, int location) {
        
        int answer = 0;
        int l = location; // 내가 찾는 프로세스의 현재 위치

        // 우선순위 큐 생성
        Queue<Integer> que = new LinkedList<>();
        for (int i : priorities) {
            que.add(i);
        }

        // 우선순위 배열 정렬 (최대값부터 사용)
        Arrays.sort(priorities);
        int size = priorities.length - 1;

        // 큐가 빌 때까지 반복
        while (!que.isEmpty()) {
            int cur = que.poll();

            // 현재 프로세스가 가장 높은 우선순위라면 실행
            if (cur == priorities[size - answer]) {
                answer++; // 실행 순서 증가
                l--;      // 위치 이동

                // 내가 찾는 프로세스라면 종료
                if (l < 0) break;
            }
            // 더 높은 우선순위가 남아 있으면 다시 큐에 넣기
            else {
                que.add(cur);
                l--;

                // 위치가 큐 끝으로 이동하는 경우 보정
                if (l < 0) {
                    l = que.size() - 1;
                }
            }
        }

        return answer;
    }
}