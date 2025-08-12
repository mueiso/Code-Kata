class Solution {
    
    public String solution(String my_string, int m, int c) {
        
        /*
         * 문자열 결합 시 + 연산을 쓰면 매번 새로운 문자열 객체를 생성하므로 성능이 떨어질 수 있음
         * 그래서 StringBuilder 사용: 문자열을 효율적으로 이어붙이기 위해
         */
        StringBuilder sb = new StringBuilder();
        
        /*
         * my_string을 m 글자씩 가로로 나열했을 때,
         * c번째 열의 문자들을 세로로 읽는 문제.
         * 
         * 예시:
         * my_string = "abcdefghijk"
         * m = 3, c = 2
         * 
         * a b c
         * d e f
         * g h i
         * j k
         * 
         * → c번째 열 = b, e, h, k
         */
        
        // 인덱스는 0부터 시작하므로 c - 1이 첫 번째 열의 시작 위치
        for (int i = c - 1; i < my_string.length(); i += m) {
            // c번째 열의 문자를 StringBuilder에 추가
            sb.append(my_string.charAt(i));
        }
        
        // 최종적으로 완성된 문자열을 반환
        return sb.toString();
    }
}
