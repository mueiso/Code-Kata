import java.util.*;

class Solution {

    public String solution(String[] my_strings, int[][] parts) {

        // 결과를 저장할 문자열 변수 선언
        String answer = "";

        // 모든 parts 배열을 순회
        for (int i = 0; i < parts.length; i++) {
            
            /*
             * 각 my_strings[i] 문자열에서
             * parts[i][0] ~ parts[i][1] 까지의 부분 문자열을 추출
             * (substring은 끝 인덱스를 포함하지 않기 때문에 +1 필요)
             */
            String part = my_strings[i].substring(parts[i][0], parts[i][1] + 1);

            // 결과 문자열에 이어 붙임
            answer += part;
        }

        // 최종 결과 문자열 반환
        return answer;
    }
}
