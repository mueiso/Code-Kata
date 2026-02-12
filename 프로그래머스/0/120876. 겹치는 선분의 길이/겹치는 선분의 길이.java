/*
 * 겹치는 “길이” = 겹치는 “1단위 구간 개수”
 * 좌표 범위 작으면 배열 완전탐색이 가장 안전
 * 수학적 조건 비교보다 실수 확률 낮음
 */

class Solution {
    
    public int solution(int[][] lines) {
        
        int answer = 0;

        // 좌표 범위가 -100 ~ 100 이므로 200칸 배열 사용
        int[] count = new int[201];

        // 1. 각 선분을 1단위 구간으로 나누어 카운트
        for (int[] line : lines) {
            int start = line[0] + 100;  // 음수 보정
            int end = line[1] + 100;

            for (int i = start; i < end; i++) {
                count[i]++;  // 해당 구간을 지나는 선분 개수 증가
            }
        }

        // 2. 2개 이상 겹치는 구간 개수 세기
        for (int i = 0; i < 201; i++) {
            if (count[i] >= 2) {
                answer++;
            }
        }

        return answer;
    }
}