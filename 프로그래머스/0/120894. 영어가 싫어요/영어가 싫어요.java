class Solution {
    public long solution(String numbers) {
        // 숫자 단어와 대응되는 숫자 문자열을 배열로 준비
        String[] numWords = {"zero", "one", "two", "three", "four",
                             "five", "six", "seven", "eight", "nine"};

        // numbers 문자열을 각 단어별로 숫자 문자열로 바꾸기
        for (int i = 0; i < 10; i++) {
            numbers = numbers.replaceAll(numWords[i], Integer.toString(i));
        }

        // 변환된 문자열을 long 타입으로 변환 후 반환
        return Long.parseLong(numbers);
    }
}
