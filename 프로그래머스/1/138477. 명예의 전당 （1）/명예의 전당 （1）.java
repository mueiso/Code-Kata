import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소 힙 (명예의 전당)
        
        for (int i = 0; i < score.length; i++) {
            pq.offer(score[i]); // 점수 추가
            
            if (pq.size() > k) {
                pq.poll(); // 명예의 전당에서 가장 낮은 점수 제거
            }
            
            answer[i] = pq.peek(); // 현재 명예의 전당 최하위 점수
        }
        
        return answer;
    }
}