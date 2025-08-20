class Solution {
    public int[] solution(int[] arr, int[][] intervals) {

        // 첫 번째 구간의 시작, 끝 / 두 번째 구간의 시작, 끝
        int s1 = intervals[0][0], e1 = intervals[0][1];
        int s2 = intervals[1][0], e2 = intervals[1][1];

        // 결과 배열 크기 = (첫 구간 길이) + (두 구간 길이)
        int[] answer = new int[(e1 - s1 + 1) + (e2 - s2 + 1)];
        
        int idx = 0; // answer 배열에 값을 채워 넣기 위한 인덱스

        // 첫 번째 구간을 잘라 answer에 복사
        for (int i = s1; i <= e1; i++) {
            answer[idx++] = arr[i];
        }

        // 두 번째 구간을 잘라 answer에 이어 붙임
        for (int i = s2; i <= e2; i++) {
            answer[idx++] = arr[i];
        }

        return answer;
    }
}
