class Solution {
    public int solution(int[] numbers, int k) {
        // 공을 받는 사람은 1번부터 시작, 오른쪽으로 한 명씩 건너뛰며 공을 던짐
        // 즉, 처음 1번 -> 3번 -> 5번 ... 이렇게 건너뛰며 던짐
        // k번째 던지는 사람은 numbers 배열에서 (2 * (k - 1)) % numbers.length 번째 인덱스임
        
        int idx = (2 * (k - 1)) % numbers.length;
        return numbers[idx];
    }
}
