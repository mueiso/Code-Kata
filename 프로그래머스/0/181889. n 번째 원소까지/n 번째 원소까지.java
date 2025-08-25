class Solution {
    
    public int[] solution(int[] num_list, int n) {
        
        // n개의 원소를 저장할 배열 생성
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            // 앞에서부터 n개 복사
            answer[i] = num_list[i];
        }
        
        return answer;
    }
}
