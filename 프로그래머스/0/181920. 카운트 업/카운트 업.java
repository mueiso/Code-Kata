class Solution {
    
    public int[] solution(int start_num, int end_num) {
        
        // start_num부터 end_num까지의 개수만큼 배열 생성
        int[] answer = new int[end_num - start_num + 1];

        // start_num부터 end_num까지 순차적으로 값을 채워 넣기
        for (int i = 0; i < answer.length; i++) {
            answer[i] = start_num + i;
        }

        return answer;
    }
}
