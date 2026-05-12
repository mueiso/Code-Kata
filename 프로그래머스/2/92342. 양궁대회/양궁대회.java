class Solution {

    // 최대 점수 차
    private int maxDiff = 0;

    // 정답 배열
    private int[] answer = {-1};

    public int[] solution(int n, int[] info) {

        // 라이언 화살 기록
        int[] lion = new int[11];

        // DFS 탐색 시작
        dfs(0, n, info, lion);

        return answer;
    }

    /* DFS 탐색 */
    private void dfs(int idx, int arrows,
                     int[] info, int[] lion) {

        // 마지막 점수까지 확인 완료
        if (idx == 11) {

            // 남은 화살은 0점에 몰아주기
            if (arrows > 0) {
                lion[10] += arrows;
            }

            // 점수 계산
            int diff = getScoreDiff(info, lion);

            // 라이언 승리인 경우만 확인
            if (diff > 0) {

                // 더 큰 점수 차 발견
                if (diff > maxDiff) {

                    maxDiff = diff;
                    answer = lion.clone();
                }

                // 점수 차 같으면 낮은 점수 많이 맞힌 경우 선택
                else if (diff == maxDiff) {

                    if (isBetter(lion, answer)) {
                        answer = lion.clone();
                    }
                }
            }

            // 원상복구
            if (arrows > 0) {
                lion[10] -= arrows;
            }

            return;
        }

        // 현재 점수
        int score = 10 - idx;

        // 1. 현재 점수를 이기는 경우
        int need = info[idx] + 1;

        if (arrows >= need) {

            lion[idx] = need;

            dfs(idx + 1,
                arrows - need,
                info,
                lion);

            // 백트래킹
            lion[idx] = 0;
        }

        // 2. 현재 점수를 포기하는 경우
        dfs(idx + 1, arrows, info, lion);
    }

    /* 점수 차 계산 */
    private int getScoreDiff(int[] info, int[] lion) {

        int apeach = 0;
        int ryan = 0;

        for (int i = 0; i < 11; i++) {

            int score = 10 - i;

            // 둘 다 못 맞힌 경우
            if (info[i] == 0 && lion[i] == 0) {
                continue;
            }

            // 라이언이 점수 획득
            if (lion[i] > info[i]) {
                ryan += score;
            }

            // 어피치가 점수 획득
            else {
                apeach += score;
            }
        }

        return ryan - apeach;
    }

    /* 낮은 점수를 더 많이 맞힌 경우 비교 */
    private boolean isBetter(int[] lion,
                             int[] answer) {

        // 0점부터 역순 비교
        for (int i = 10; i >= 0; i--) {

            // 더 많이 맞힌 경우 우선
            if (lion[i] > answer[i]) {
                return true;
            }

            if (lion[i] < answer[i]) {
                return false;
            }
        }

        return false;
    }
}