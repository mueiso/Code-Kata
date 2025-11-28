import java.util.*;

class Solution {
    
    public int[] solution(int k, int[] score) {

        int[] answer = new int[score.length];
        PriorityQueue<Integer> hall = new PriorityQueue<>();  // 명예의 전당(최소 힙)

        for (int i = 0; i < score.length; i++) {

            // 오늘 점수 추가
            hall.add(score[i]);

            // 명예의 전당이 가득 차면, 가장 낮은 점수 제거
            if (hall.size() > k) {
                hall.poll();
            }

            // 현재 명예의 전당 중 가장 낮은 점수 기록
            answer[i] = hall.peek();
        }

        return answer;
    }
}