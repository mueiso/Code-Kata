class Solution {
    public int solution(int n) {
        int count = 0; // 현재까지 찾은 3x 마을 숫자 개수
        int number = 0; // 실제 숫자를 증가시키며 찾음

        while (count < n) {
            number++;
            // 3의 배수도 아니고, 숫자에 3이 포함되지 않는 경우만 카운트
            if (number % 3 != 0 && !String.valueOf(number).contains("3")) {
                count++;
            }
        }

        return number;
    }
}
