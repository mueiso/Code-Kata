import java.util.Arrays;

class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);  // 오름차순 정렬

        int n = numbers.length;
        int max1 = numbers[n-1] * numbers[n-2];  // 가장 큰 두 수의 곱
        int max2 = numbers[0] * numbers[1];      // 가장 작은 두 수의 곱 (음수일 경우)

        return Math.max(max1, max2);
    }
}
