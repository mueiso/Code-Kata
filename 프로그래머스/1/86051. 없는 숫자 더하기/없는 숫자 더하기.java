public class Solution {
    public int solution(int[] numbers) {
        // 0부터 9까지의 숫자 합
        int totalSum = 45;
        
        // numbers 배열의 모든 숫자를 더한 값
        int sumOfNumbers = 0;
        for (int num : numbers) {
            sumOfNumbers += num;
        }
        
        // 0부터 9까지의 숫자 합에서 numbers 배열의 합을 빼면 구할 수 있다
        return totalSum - sumOfNumbers;
    }
}
