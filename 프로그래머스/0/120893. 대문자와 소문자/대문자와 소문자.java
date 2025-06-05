class Solution {
    public String solution(String my_string) {
        StringBuilder answer = new StringBuilder();
        
        for (char ch : my_string.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                answer.append(Character.toLowerCase(ch));
            } else if (Character.isLowerCase(ch)) {
                answer.append(Character.toUpperCase(ch));
            } else {
                answer.append(ch); // 공백, 숫자 등 다른 문자는 그대로
            }
        }
        
        return answer.toString();
    }
}
