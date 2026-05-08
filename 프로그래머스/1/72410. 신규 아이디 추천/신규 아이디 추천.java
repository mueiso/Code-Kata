class Solution {

    public String solution(String new_id) {

        // 1단계: 대문자 -> 소문자
        String answer = new_id.toLowerCase();

        // 2단계: 허용 문자 제외 모두 제거
        // 허용: 소문자, 숫자, -, _, .
        answer = answer.replaceAll("[^a-z0-9-_.]", "");

        // 3단계: 연속된 '.' -> '.'
        answer = answer.replaceAll("[.]{2,}", ".");

        // 4단계: 처음이나 끝의 '.' 제거
        answer = answer.replaceAll("^[.]|[.]$", "");

        // 5단계: 빈 문자열이면 "a"
        if (answer.isEmpty()) {
            answer = "a";
        }

        // 6단계: 길이 15 초과면 자르기
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
        }

        // 끝이 '.'이면 제거
        answer = answer.replaceAll("[.]$", "");

        // 7단계: 길이가 2 이하이면 마지막 문자 반복
        while (answer.length() < 3) {
            answer += answer.charAt(answer.length() - 1);
        }

        return answer;
    }
}