import java.util.*;

class Solution {
    
    public int solution(int k, int m, int[] score) {
        
        int answer = 0;

        Arrays.sort(score); // 오름차순 정렬

        // i는 "상자에서 가장 낮은 점수의 인덱스"
        // 뒤에서부터 m개씩 묶을 거니까 시작점은 length - m
        for (int i = score.length - m; i >= 0; i -= m) {
            // i가 현재 상자에서의 최소 점수 위치
            answer += score[i] * m;
        }

        return answer;
    }
}