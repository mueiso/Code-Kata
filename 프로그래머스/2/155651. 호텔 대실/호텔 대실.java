import java.util.*;

class Solution {
    
    public int solution(String[][] book_time) {
        
        // 시간을 분으로 변환해서 저장
        int[][] times = new int[book_time.length][2];
        
        for (int i = 0; i < book_time.length; i++) {
            
            int start = convert(book_time[i][0]);
            int end = convert(book_time[i][1]) + 10; // 청소시간 포함
            
            times[i][0] = start;
            times[i][1] = end;
        }
        
        // 시작 시간 기준 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);
        
        // 종료 시간 관리 (가장 빨리 끝나는 방)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int[] t : times) {
            
            int start = t[0];
            int end = t[1];
            
            // 가장 빨리 끝난 방이 사용 가능하면 제거
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }
            
            // 현재 예약의 종료 시간 추가
            pq.offer(end);
        }
        
        // 큐 크기 = 필요한 방 개수
        return pq.size();
    }
    
    // "HH:MM" → 분 변환
    private int convert(String time) {
        
        String[] t = time.split(":");
        
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}