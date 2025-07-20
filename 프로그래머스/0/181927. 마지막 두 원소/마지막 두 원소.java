class Solution {
    
    public int[] solution(int[] num_list) {
        
        // 1. 새 배열 생성 (기존보다 한 칸 더 큼)
        int[] answer = new int[num_list.length+1];

        // 2. 기존 배열 복사
        for(int i=0; i<num_list.length; i++){
            answer[i] = num_list[i];
        }

        // 3. 마지막 원소와 그전 원소 비교 → 조건에 따라 새로운 값 계산
        answer[num_list.length] = 
            num_list[num_list.length-1] > num_list[num_list.length-2] 
            ? num_list[num_list.length-1] - num_list[num_list.length-2]
            : num_list[num_list.length-1] * 2;

        return answer;
    }
}
