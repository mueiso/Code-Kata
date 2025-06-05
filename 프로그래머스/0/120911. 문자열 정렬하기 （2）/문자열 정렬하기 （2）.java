import java.util.*;

class Solution {
    public String solution(String my_string) {
        // 소문자로 변환
        my_string = my_string.toLowerCase();

        // 문자 배열로 변환 후 정렬
        char[] chars = my_string.toCharArray();
        Arrays.sort(chars);

        // 정렬된 문자 배열을 문자열로 변환
        return new String(chars);
    }
}
