import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(String my_string) {
        ArrayList<Integer> numbers = new ArrayList<>();

        // 문자열에서 숫자만 추출
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);
            if (Character.isDigit(c)) {
                numbers.add(c - '0');  // 문자 '0'~'9'를 숫자로 변환
            }
        }

        // 오름차순 정렬
        Collections.sort(numbers);

        // ArrayList -> int[] 변환
        int[] answer = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            answer[i] = numbers.get(i);
        }

        return answer;
    }
}
