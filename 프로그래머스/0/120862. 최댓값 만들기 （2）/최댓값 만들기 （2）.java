class Solution {
    public int solution(int[] numbers) {
        int answer = Integer.MIN_VALUE; // 최소값으로 초기화

        // 두 수를 곱한 값 중 최댓값 찾기
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int product = numbers[i] * numbers[j];
                if (product > answer) {
                    answer = product;
                }
            }
        }

        return answer;
    }
}
