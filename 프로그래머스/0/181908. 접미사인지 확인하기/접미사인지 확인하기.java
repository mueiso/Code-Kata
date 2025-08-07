class Solution {
    
    public int solution(String my_string, String is_suffix) {
        
        // endsWith 메서드를 사용하면 문자열이 특정 문자열로 끝나는지 확인할 수 있음
        if (my_string.endsWith(is_suffix)) {
            
            return 1; // 접미사가 맞는 경우
        } else {
            
            return 0; // 접미사가 아닌 경우
        }
    }
}
