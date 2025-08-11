class Solution {
    
    public String solution(String my_string, int s, int e) {
        
        /*
         * my_string의 s ~ e 구간을 잘라내어 StringBuilder에 저장
         * StringBuilder를 사용하면 reverse() 메서드로 쉽게 문자열을 뒤집을 수 있음
         */
        StringBuilder answer = new StringBuilder(my_string.substring(s, e + 1));
        
        // s ~ e 구간의 문자열을 뒤집음
        answer.reverse();
        
        /*
         * 최종 결과 생성:
         * 1. my_string의 앞부분 (0 ~ s-1)
         * 2. 뒤집은 문자열(answer)
         * 3. my_string의 뒷부분 (e+1 ~ 끝)
         */
        return my_string.substring(0, s) + answer + my_string.substring(e + 1);
    }
}
