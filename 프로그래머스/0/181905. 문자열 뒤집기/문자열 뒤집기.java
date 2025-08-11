class Solution {
    
    public String solution(String my_string, int s, int e) {
        
        /*
         * StringBuilder를 사용하면 문자열 일부를 쉽게 뒤집을 수 있음
         * 1. s 이전의 부분은 그대로 둔다.
         * 2. s ~ e 구간은 뒤집는다.
         * 3. e 이후의 부분을 그대로 이어 붙인다.
         */
        
        // 1. s 이전 문자열
        String prefix = my_string.substring(0, s);
        
        // 2. s~e 구간을 뒤집기 위해 StringBuilder 사용
        String reversedPart = new StringBuilder(my_string.substring(s, e + 1)).reverse().toString();
        
        // 3. e 이후 문자열
        String suffix = my_string.substring(e + 1);
        
        // 세 부분을 합쳐서 반환
        return prefix + reversedPart + suffix;
    }
}
