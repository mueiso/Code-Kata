import java.util.*;

class Solution {
    
    public int[] solution(int[] answers) {
        
        // 각 수포자의 찍기 패턴
        int[] pattern1 = {1, 2, 3, 4, 5};
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        // 수포자 1, 2, 3의 정답 개수
        int[] score = new int[3];

        // 각 문제에 대해 세 명의 수포자와 정답 비교
        for (int i = 0; i < answers.length; i++) {
            // i번째 문제에서 각 패턴에서의 "찍은 값"은
            // 패턴 길이로 나눈 나머지 인덱스로 접근
            if (answers[i] == pattern1[i % pattern1.length]) score[0]++;
            if (answers[i] == pattern2[i % pattern2.length]) score[1]++;
            if (answers[i] == pattern3[i % pattern3.length]) score[2]++;
        }

        // 세 명 중 최고 점수 구하기
        int max = Math.max(score[0], Math.max(score[1], score[2]));

        // 최고 점수를 받은 사람(들)을 리스트에 담기
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (score[i] == max) {
                result.add(i + 1); // 수포자 번호는 1번부터 시작
            }
        }

        // List<Integer> → int[] 변환
        return result.stream()
                     .mapToInt(i -> i)
                     .toArray();
    }
}