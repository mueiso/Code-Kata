import java.util.Arrays;

class Solution {
    
    public int solution(int a, int b, int c, int d) {

        int[] dice = { a, b, c, d };
        // 주사위 값을 정렬하여 패턴 인식을 쉽게 만듦
        Arrays.sort(dice);

        int ans = 0;

        /* [조건 1] 
         * 네 주사위가 모두 같은 값일 때: 1111 * p
         */
        if (dice[0] == dice[3]) {
            ans = 1111 * dice[3];

        /* [조건 2]
         * 세 개가 같은 값이고, 나머지 하나는 다른 경우: (10 * p + q)^2
         * ex) [2, 4, 4, 4] 또는 [4, 4, 4, 2]
         */
        } else if (dice[0] == dice[2] || dice[1] == dice[3]) {
            // dice[1]이 공통값(p), dice[0] + dice[3] - dice[1]은 나머지 한 수(q)
            ans = (int) Math.pow(dice[1] * 10 + (dice[0] + dice[3] - dice[1]), 2);

        /* [조건 3]
         * 두 쌍씩 같은 경우: (p + q) * |p - q|
         * ex) [2, 2, 5, 5]
         */
        } else if (dice[0] == dice[1] && dice[2] == dice[3]) {
            ans = (dice[0] + dice[3]) * (dice[3] - dice[0]);

        /* [조건 4]
         * 하나는 쌍이고 나머지는 서로 다른 경우: q * r
         * ex) [2, 2, 3, 6]
         * 세 가지 경우의 쌍 위치를 나누어 계산
         */
        } else if (dice[0] == dice[1]) {
            ans = dice[2] * dice[3];
        } else if (dice[1] == dice[2]) {
            ans = dice[0] * dice[3];
        } else if (dice[2] == dice[3]) {
            ans = dice[0] * dice[1];

        /* [조건 5]
         * 모두 다른 값일 때: 가장 작은 값
         */
        } else {
            ans = dice[0];
        }

        return ans;
    }
}
