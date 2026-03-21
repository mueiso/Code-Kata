class Solution {
    
    public long solution(int[] weights) {
        
        long answer = 0;
        
        int[] count = new int[1001];
        
        // 개수 세기
        for (int w : weights) {
            
            count[w]++;
        }
        
        // 1:1 (같은 무게)
        for (int i = 100; i <= 1000; i++) {
            
            if (count[i] > 1) {
                answer += (long) count[i] * (count[i] - 1) / 2;
            }
        }
        
        // 2:3
        for (int i = 100; i <= 1000; i++) {
            
            if (i * 3 <= 1000 * 2 && (i * 3) % 2 == 0) {
                int target = i * 3 / 2;
                
                if (target <= 1000) {
                    answer += (long) count[i] * count[target];
                }
            }
        }
        
        // 2:4 (== 1:2)
        for (int i = 100; i <= 1000; i++) {
            
            int target = i * 2;
            
            if (target <= 1000) {
                answer += (long) count[i] * count[target];
            }
        }
        
        // 3:4
        for (int i = 100; i <= 1000; i++) {
            
            if ((i * 4) % 3 == 0) {
                int target = i * 4 / 3;
                
                if (target <= 1000) {
                    answer += (long) count[i] * count[target];
                }
            }
        }
        
        return answer;
    }
}