class Solution {
    public String solution(String my_string, int num1, int num2) {
        char[] chars = my_string.toCharArray();

        // 문자 교환
        char temp = chars[num1];
        chars[num1] = chars[num2];
        chars[num2] = temp;

        return new String(chars);
    }
}
