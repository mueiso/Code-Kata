import java.util.Arrays;

class Solution {
    
    public double solution(int[] numbers) {
        
        /*
         * Arrays.stream(numbers)
           int[] 배열을 IntStream으로 변환
           예: [1, 2, 3] → IntStream(1, 2, 3)
           
         * average()
           스트림 내 숫자들의 평균을 계산.
           결과 타입은 OptionalDouble (비어있을 수 있기 때문에)
           
         * orElse(0)
           만약 numbers가 빈 배열이라 평균이 없을 경우, 기본값 0을 반환
         */
        return Arrays.stream(numbers).average().orElse(0);
    }
}