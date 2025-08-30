import java.util.Arrays;

class Solution {
    
    public int solution(int[] num_list) {
        
        /*
         * Arrays.stream(num_list) → 배열을 스트림으로 변환
         
         * .map(i -> Integer.toBinaryString(i).length() - 1)
            - 정수를 2진수 문자열로 변환 (예: 10 -> "1010")
            - 2진수의 자릿수 - 1 = 1이 될 때까지 필요한 나누기 연산 횟수
            (예: "1010".length() = 4 → 3번의 연산 필요)
            
         * .sum() → 모든 원소의 연산 횟수를 합산
         */
        return Arrays.stream(num_list)
                     .map(i -> Integer.toBinaryString(i).length() - 1)
                     .sum();
    }
}
