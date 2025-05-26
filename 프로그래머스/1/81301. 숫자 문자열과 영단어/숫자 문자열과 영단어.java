import java.util.*;

class Solution {
    public int solution(String s) {
        // 숫자 단어와 숫자를 매핑
        String[] words = {"zero", "one", "two", "three", "four", 
                          "five", "six", "seven", "eight", "nine"};

        // 각 단어를 숫자로 치환
        for (int i = 0; i < words.length; i++) {
            s = s.replaceAll(words[i], Integer.toString(i));
        }

        // 변환된 문자열을 정수로 변환
        return Integer.parseInt(s);
    }
}
