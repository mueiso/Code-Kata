import java.util.PriorityQueue;

class Solution {
    
    public int solution(int[] scoville, int K) {
        
        int answer = 0;
        
        // 낮은 숫자가 높은 우선순위를 갖는 최소 힙(Min Heap) 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 모든 스코빌 지수를 우선순위 큐에 삽입
        for (int s : scoville) {
            pq.add(s);
        }
        
        // 가장 매운맛이 K 미만일 때만 루프 수행
        while (pq.peek() < K) {
            // 더 이상 섞을 음식을 꺼낼 수 없다면 (모든 음식을 섞었는데도 K 미만인 경우)
            if (pq.size() < 2) {
                return -1;
            }
            
            // 가장 안 매운 음식 2개 추출
            int first = pq.poll();
            int second = pq.poll();
            
            // 새 음식 생성 후 큐에 삽입
            int mixed = first + (second * 2);
            pq.add(mixed);
            
            answer++;
        }
        
        return answer;
    }
}