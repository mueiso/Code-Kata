class Solution {
    
    public int solution(int[] num_list) {
        
        // 배열을 처음부터 끝까지 탐색
        for (int i = 0; i < num_list.length; i++) {
            
            // 만약 음수를 만나면 해당 인덱스를 반환
            if (num_list[i] < 0) {
                
                return i;
            }
        }
        
        // 끝까지 탐색했는데 음수가 없다면 -1 반환
        return -1;
    }
}
