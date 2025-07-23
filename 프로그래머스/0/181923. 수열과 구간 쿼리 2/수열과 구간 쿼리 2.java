import java.util.Arrays;

class Solution {
    
    public int[] solution(int[] arr, int[][] queries) {

        // queries.length 크기의 정답 배열 생성
        int[] answer = new int[queries.length];
        // 처음에는 모든 값을 -1로 초기화 (조건을 만족하는 값이 없을 경우를 대비)
        Arrays.fill(answer, -1);

        /*
         * 각 쿼리 [s, e, k]를 추출
         * arr[s]부터 arr[e]까지 순회하면서 k보다 큰 수 중 가장 작은 값을 찾음
         */
        for (int idx = 0; idx < queries.length; idx++) {
            int[] query = queries[idx];
            int s = query[0], e = query[1], k = query[2];

            /*
             * arr[i] > k 조건을 만족하면:
                answer[idx]가 아직 -1이면 arr[i]로 초기화
                이미 값이 있다면 Math.min()을 통해 더 작은 값으로 갱신
             * 이 구조 덕분에 별도의 min 변수 없이 answer 배열만으로 최소값을 관리할 수 있음
             */
            for (int i = s; i <= e; i++) {
                if (k < arr[i]) {
                    answer[idx] = answer[idx] == -1 ? arr[i] : Math.min(answer[idx], arr[i]);
                }
            }

        }

        return answer;
    }
}
