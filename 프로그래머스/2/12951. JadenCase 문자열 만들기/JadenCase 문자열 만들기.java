class Solution {
    
    public String solution(String s) {

        StringBuilder answer = new StringBuilder();
        boolean isFirst = true; // 단어의 첫 문자 여부

        for (char c : s.toCharArray()) {

            if (c == ' ') {
                // 공백이면 그대로 추가하고 다음 문자를 단어의 시작으로 설정
                answer.append(c);
                isFirst = true;
            } else {
                if (isFirst) {
                    // 단어의 첫 문자면 대문자로
                    answer.append(Character.toUpperCase(c));
                    isFirst = false;
                } else {
                    // 그 외 문자는 소문자로
                    answer.append(Character.toLowerCase(c));
                }
            }
        }

        return answer.toString();
    }
}