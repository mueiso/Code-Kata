class Solution {
    
    public int[] solution(int n, int[] slicer, int[] num_list) {

        /*
         * n에 따라 슬라이싱 시작 인덱스를 결정
         * n == 1 : num_list[0]부터 시작
         * 나머지 : slicer[0]부터 시작
         */
        int start = n == 1 ? 0 : slicer[0];

         /*
         * n에 따라 슬라이싱 끝 인덱스를 결정
         * n == 2 : num_list 마지막 인덱스까지
         * 나머지 : slicer[1]까지
         */
        int end = n == 2 ? num_list.length - 1 : slicer[1];

        /*
         * n == 4일 때만 slicer[2] 간격(step) 사용
         * 나머지는 step = 1
         */
        int step = n == 4 ? slicer[2] : 1;

        /*
         * 결과 배열 크기 계산
         * (end - start + step) / step → 선택되는 원소 개수
         */
        int[] answer = new int[(end - start + step) / step];

        /*
         * 실제 슬라이싱 수행
         * start부터 end까지 step 간격으로 num_list 요소를 가져와 answer에 저장
         */
        for (int i = start, j = 0; i <= end; i += step) {
            answer[j++] = num_list[i];
        }
        
        return answer;
    }
}
