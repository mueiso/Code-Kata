class Solution {

    public String solution(String my_string, int m, int c) {
        
        // 결과 문자열을 저장할 변수
        String answer = "";

        /*
         * m : 한 줄에 들어가는 글자 수
         * c : 선택할 열 번호 (1부터 시작)
         * 
         * i = c - 1 → 배열 인덱스는 0부터 시작하므로 -1 해줌
         * 이후 m씩 건너뛰면서 해당 위치의 문자 선택
         */
        for (int i = c - 1; i < my_string.length(); i += m) {
            /*
             * i번째 문자를 answer에 이어 붙임
             * 문자열 + 연산은 매번 새로운 String 객체를 생성하므로 매우 긴 문자열의 경우 비효율적일 수 있음
             */
            answer += my_string.charAt(i);
        }
        
        // 완성된 문자열 반환
        return answer;
    }
}
