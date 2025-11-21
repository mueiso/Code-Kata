class Solution {
    
    public int solution(int[] num_list, int n) {
        
        for (int num : num_list) {
            if (num == n) {
                return 1;  // 찾으면 1 즉시 반환
            }
        }
        return 0;  // 끝까지 못 찾으면 0
    }
}