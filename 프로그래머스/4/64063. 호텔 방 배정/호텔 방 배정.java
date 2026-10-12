import java.util.HashMap;
import java.util.Map;

class Solution {
    
    private Map<Long, Long> parent = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            answer[i] = find(room_number[i]);
        }

        return answer;
    }

    /* 비어 있는 방을 찾아 반환하고 경로 압축을 수행하는 메서드 */
    private long find(long x) {
        
        // 만약 맵에 기록되어 있지 않다면, 해당 방은 비어 있으므로 즉시 배정
        if (!parent.containsKey(x)) {
            parent.put(x, x + 1);
            
            return x;
        }

        // 이미 배정된 방이라면 다음 후보 방을 재귀적으로 찾고 경로 압축 적용
        long next = find(parent.get(x));
        parent.put(x, next);
        
        return next;
    }
}