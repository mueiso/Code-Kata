/*
 * 병사는 작은 적부터 쓰는 게 이득
 * 큰 적은 무적권으로 막아야 이득
 * 지금까지 나온 적 중에서 가장 큰 k개를 무적권으로 처리
 */

import java.util.*;

class Solution {
    
    public int solution(int n, int k, int[] enemy) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int answer = 0;

        for (int i = 0; i < enemy.length; i++) {
            pq.offer(enemy[i]);   // 일단 병사로 막았다고 가정
            n -= enemy[i];

            // 병사가 부족해졌을 때
            if (n < 0) {
                if (k > 0) {
                    // 가장 큰 적을 무적권으로 처리 (병사 복구)
                    n += pq.poll();
                    k--;
                } else {
                    // 더 이상 못 막음
                    break;
                }
            }

            answer++;
        }

        return answer;
    }
}