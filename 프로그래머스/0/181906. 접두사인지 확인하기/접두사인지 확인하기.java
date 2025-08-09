class Solution {
    
    public int solution(String my_string, String is_prefix) {
        
        /*
         * startsWith(String prefix) 메서드는
         * 해당 문자열이 prefix로 시작하면 true, 아니면 false를 반환함
         */
        if (my_string.startsWith(is_prefix)) {
            return 1; // 접두사가 맞으면 1 반환
        }
        
        return 0; // 접두사가 아니면 0 반환
    }
}
