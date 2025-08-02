class Solution {
    
    public int solution(String number) {

        /*
         * 문자열 number를 정수 스트림으로 변환하고,
         * 각 문자에서 '0'을 빼서 정수로 바꾼 뒤, 각 자리수를 9로 나눈 나머지를 누적합한 후,
         * 그 결과를 다시 9로 나눈 나머지를 반환한다.
         */
        return number
                .chars()                                // 문자열의 각 문자를 IntStream으로 변환 (유니코드 값)
                .map(c -> (c - '0') % 9)                // 문자 '0'을 빼서 숫자로 변환 후 9로 나눈 나머지 계산
                .sum() % 9;                             // 모든 자리 수의 (mod 9) 결과를 더한 뒤, 다시 9로 나눈 나머지 반환
    }
}
