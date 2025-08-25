class Solution {
    public int[] solution(int[] num_list, int n) {
        /*
         * 전체 배열 길이에서 n 간격으로 뽑았을 때 몇 개의 원소가 나오는지 계산
         * (ex: 길이가 10이고 n=3이면 → 10/3=3, 나머지가 있으므로 하나 더 → 4개)
         */
        int N = num_list.length % n == 0 ? num_list.length / n : num_list.length / n + 1;

        // answer 배열에 원소를 담을 위치를 표시할 인덱스
        int idx = 0;

        // 결과를 저장할 배열 선언
        int[] answer = new int[N];

        // i를 n씩 증가시키면서 원소를 뽑아 answer에 저장
        for (int i = 0; i < num_list.length; i += n) {
            answer[idx++] = num_list[i];
        }

        return answer;
    }
}
