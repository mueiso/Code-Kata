class Solution {
    public int solution(int hp) {
        int answer = 0;

        answer += hp / 5;   // 장군개미 수
        hp %= 5;

        answer += hp / 3;   // 병정개미 수
        hp %= 3;

        answer += hp;       // 일개미 수 (남은 체력 만큼)

        return answer;
    }
}
