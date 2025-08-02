class Solution {
    
    public int solution(String number) {

        // 모든 자릿수의 합을 저장할 변수
        int sum = 0;

        // 문자열로 주어진 숫자의 각 문자를 순회
        for (char c : number.toCharArray()) {
            // 각 문자를 숫자로 변환하여 sum에 누적
            sum += c - '0';
        }

        // 자릿수의 합을 9로 나눈 나머지를 반환
        return sum % 9;
    }
}
