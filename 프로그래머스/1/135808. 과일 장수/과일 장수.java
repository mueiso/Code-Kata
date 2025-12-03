import java.util.*;

class Solution {
    
    public int solution(int k, int m, int[] score) {
        
        Arrays.sort(score); // 오름차순 정렬
        int n = score.length;
        int profit = 0;

        // 뒤에서부터 m개씩 묶어서 최소 점수 * m만큼 수익을 계산
        for (int i = n - m; i >= 0; i -= m) {
            profit += score[i] * m;
        }

        return profit;
    }
}