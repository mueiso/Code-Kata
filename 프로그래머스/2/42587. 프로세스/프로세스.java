import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    public int solution(int[] priorities, int location) {
        
        int answer = 0;

        // [우선순위, 원래 위치]를 함께 저장하는 큐
        Queue<int[]> queue = new LinkedList<>();

        // 큐에 초기 데이터 저장
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{priorities[i], i});
        }

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            int[] current = queue.poll(); // 맨 앞 프로세스 꺼내기
            boolean hasHigherPriority = false;

            // 큐 안에 더 높은 우선순위가 있는지 확인
            for (int[] q : queue) {
                if (q[0] > current[0]) {
                    hasHigherPriority = true;
                    break;
                }
            }

            // 더 높은 우선순위가 있으면 다시 큐에 넣기
            if (hasHigherPriority) {
                queue.offer(current);
            } 
            // 없으면 실행
            else {
                answer++; // 실행 순서 증가

                // 우리가 찾는 프로세스라면 결과 반환
                if (current[1] == location) {
                    return answer;
                }
            }
        }

        return answer;
    }
}