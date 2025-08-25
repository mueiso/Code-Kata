class Solution {
    
    public int[] solution(int[] num_list, int n) {
        
        // 결과 배열의 크기 = num_list 길이를 n으로 나눈 값 (올림 처리)
        int size = (num_list.length + n - 1) / n;

        // 결과를 저장할 배열 선언
        int[] answer = new int[size];

        // n 간격으로 원소를 가져오기
        for (int i = 0, j = 0; i < num_list.length; i += n, j++) {
            answer[j] = num_list[i];  // i번째 원소를 answer에 순서대로 저장
        }

        return answer;  // 결과 배열 반환
    }
}
