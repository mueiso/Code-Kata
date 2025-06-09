import java.util.*;

class Solution {
    public int[] solution(int[][] score) {
        int n = score.length;
        double[] avg = new double[n];
        Integer[] rankIndex = new Integer[n];
        
        // 평균 점수 계산
        for (int i = 0; i < n; i++) {
            avg[i] = (score[i][0] + score[i][1]) / 2.0;
            rankIndex[i] = i; // 원래 인덱스 저장
        }

        // 평균 점수 기준으로 등수 매기기
        Arrays.sort(rankIndex, (a, b) -> Double.compare(avg[b], avg[a]));

        int[] answer = new int[n];
        int rank = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0 && avg[rankIndex[i]] == avg[rankIndex[i - 1]]) {
                answer[rankIndex[i]] = answer[rankIndex[i - 1]]; // 공동등수
            } else {
                answer[rankIndex[i]] = i + 1;
            }
        }

        return answer;
    }
}
