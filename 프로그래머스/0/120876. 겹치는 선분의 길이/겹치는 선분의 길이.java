/*
 * 조건 분기 없이 명확함
   수학적 교집합 계산보다 실수 위험 적음

 * 좌표 범위 활용
   -100 ~ 100 → 총 200번 반복
   시간 복잡도 거의 무시 가능

 * 공간 추가 사용 없음
   이전 배열 방식보다 메모리도 더 적게 사용
 */

class Solution {
    
    public int solution(int[][] lines) {
        
        int answer = 0;

        // 좌표 범위가 -100 ~ 100 이므로 1단위 구간 [-100,100) 완전 탐색
        for (int i = -100; i < 100; i++) {
            int line = 0;

            // 구간 [i, i+1)이 각 선분에 포함되는지 확인
            if (lines[0][0] <= i && lines[0][1] > i) line++;
            if (lines[1][0] <= i && lines[1][1] > i) line++;
            if (lines[2][0] <= i && lines[2][1] > i) line++;

            // 두 개 이상 겹치는 구간이면 길이 1 증가
            if (line > 1) answer++;
        }

        return answer;
    }
}