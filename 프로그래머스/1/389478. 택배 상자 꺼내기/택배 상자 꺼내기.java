/*
   지그재그 구조에서 같은 cloumn으로 올라가려면
   끝까지 갔다가 방향 바뀌어서 같은 column으로 오는 거리 = (끝까지 거리 × 2) + 1
 */

class Solution {
    
    public int solution(int n, int w, int num) {

        int cnt = 0;

        // num이 존재하는 동안 계속 위로 점프
        while (num <= n) {

            /* [현재 위치에서 같은 column의 위 박스로 이동하는 거리 계산]
               (num - 1) % w ➡️ 현재 row에서의 기본 위치 (왼→오 기준 col)
               w - ((num-1) % w) - 1 ➡️ 현재 위치에서 반대쪽 끝까지 거리
               * 2 + 1 ➡️ 한 층 올라갈 때 필요한 이동 거리
             */
            int step = (w - ((num - 1) % w) - 1) * 2 + 1;

            num += step; // 다음 층으로 이동
            cnt++;       // 꺼내야 하는 박스 수 증가
        }

        return cnt;
    }
}