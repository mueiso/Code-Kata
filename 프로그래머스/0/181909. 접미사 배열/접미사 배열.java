import java.util.Arrays;

class Solution {

    public String[] solution(String my_string) {
        
        // 결과를 저장할 배열 선언: 문자열의 길이만큼 접미사가 있으므로 같은 길이로 배열 생성
        String[] answer = new String[my_string.length()];

        // my_string의 각 인덱스부터 끝까지 잘라서 접미사를 만든다
        for (int i = 0; i < answer.length; i++) {
            // substring(i, my_string.length())는 인덱스 i부터 문자열 끝까지의 부분 문자열을 의미
            answer[i] = my_string.substring(i, my_string.length());
        }

        // 모든 접미사를 사전 순으로 정렬
        Arrays.sort(answer);

        // 정렬된 접미사 배열 반환
        return answer;
    }
}
