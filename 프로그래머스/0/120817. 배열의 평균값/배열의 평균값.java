public class Solution {
    public double solution(int[] numbers) {
        int sum = 0;  // 배열의 합을 저장할 변수
        
        // 배열의 모든 원소 더하기
        for (int number : numbers) {
            sum += number;
        }
        
        // 평균 계산 후 반환
        return sum / (double)numbers.length;  // 결과를 double 타입으로 반환
    }
}
