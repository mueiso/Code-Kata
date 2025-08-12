class Solution {
    
    public String solution(String my_string, int m, int c) {
        
        /*
         * 문자열을 char 배열로 변환
         * → charAt() 호출 없이 인덱스로 바로 접근 가능
         * → 아주 큰 문자열에서는 미세하게 성능 향상 가능
         */
        char[] chars = my_string.toCharArray();
        
        /*
         * StringBuilder: 문자열 결합 성능 최적화
         */
        StringBuilder sb = new StringBuilder();
        
        /*
         * m: 한 줄에 들어가는 글자 수
         * c: 읽고 싶은 열 번호 (1부터 시작)
         * 
         * char 배열의 인덱스:
         * 첫 번째 읽을 위치는 c-1 (0-based index)
         * 다음 읽을 위치는 (c-1) + m, 그 다음은 (c-1) + 2*m, ...
         * 
         * 즉, i = c - 1; i < chars.length; i += m
         */
        for (int i = c - 1; i < chars.length; i += m) {
            sb.append(chars[i]); // 해당 열의 문자 추가
        }
        
        // 완성된 문자열 반환
        return sb.toString();
    }
}
